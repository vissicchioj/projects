import { StringLiteralLike } from "typescript";
import {System} from "../System";
import { Hardware } from "./Hardware";
import { ClockListener } from "./imp/ClockListener";
import { MMU } from "./MMU";
import { InterruptController } from "./InterruptController";
import { Keyboard } from "./Keyboard";
import { Ascii } from "./Ascii";

//variables
var cpuClockCount: number = 0;
var pulseIndicator: string;
var cycleLogging: string;

//stepNum must begin at 0 because fetch is always the first instruction
var stepNum: number = 0;

//programCounter will be very important in letting us be aware of what memory address we are using
var programCounter: number = 0x0000;

//important for logic based instructions
var accumulator: number = 0x0000;
var xReg: number = 0x00; 
var yReg: number = 0x00;
var zFlag: number = 0x00;
var carryFlag: number = 0x00;

//temporary program counter to remember our current place 
var tempPC: number = 0x0000;

//if set to ture it will print out more information from the CPU
var cpuLog: boolean = true;

//IR holds the instruction
var IR: number = 0x00;

export class Cpu extends Hardware implements ClockListener{
    pulse(){
        cpuClockCount ++;
        if (cpuLog == true)
        {
        pulseIndicator = "recieved clock pulse - CPU Clock Count: " + cpuClockCount;
        this.log(pulseIndicator);

        //cycleLogging will display information and will keep being updated as we go through the program every clock cycle
        cycleLogging = "CPU State | Mode: 0 PC: " + this.hexString(programCounter, 4) + " IR: " + this.hexString(IR,2) + 
            " Acc: " + this.hexString(accumulator, 2) + " xReg: " + this.hexString(xReg, 2) + 
            " yReg: " + this.hexString(yReg,2) + " zFlag: " + this.hexString(zFlag,2) + " Step: " + stepNum;
        this.log(cycleLogging);
        }//cpuLog

        //will only perform one step per clock cycle
        this.step(stepNum);
    }

    //instance variables
    public mmu: MMU = new MMU(0, "MMU", true);
    public intController: InterruptController = new InterruptController(0, "Interrupt Controller", true);
    public keyboard: Keyboard = new Keyboard(0, "Keyboard", true);
    public ascii: Ascii = new Ascii(0, "Ascii", true);

    public step(step: number)
    {
        //step represents what instruction cycle step we are currently on for this clock cycle
        switch(step)
        {
            case 0:
            this.fetch(programCounter);
            break;

            case 1:
            this.decode(programCounter);
            break;

            case 2:
            this.decode2(programCounter);
            break;

            case 3:
            this.execute();
            break;

            case 4:
            this.execute2();
            break;

            case 5:
            this.writeBack();
            break;

            case 6:
            this.interruptCheck();
            break;
        }
    }

    fetch (hexNum: number)
    {
        //set the IR to the current MDR
        IR = this.mmu.readImmediate(hexNum);

        //increment program counter because we went to memory
        programCounter++;

        //console.log("fetch");
        //always go to decode next
        stepNum = 1;
    }

    decode (hexNum: number)
    {
        //load accumulator with a constant
        if (IR  == 0xA9)
        {
            //set accumulator to the MDR
            accumulator = this.mmu.readImmediate(hexNum);
            programCounter++;
            stepNum = 6;
        }

        //These instructions all use decode2 because it makes use of lob and hob
        // loading accumulator from memory || store accumulator in memory ||
        // store the x register from memory || store the y register from memory || add contents from memory to accumulator ||
        // Compare a byte in memory to the x reg, sets z flag if equal || increment a byte from memory
        if (IR == 0xAD || IR == 0x8D || IR == 0xAE || IR == 0xAC || IR == 0x6D || IR == 0xEC || IR == 0xEE)
        {
            //we need to set the lob the MDR
            this.mmu.lob = this.mmu.readImmediate(hexNum);
            programCounter++;
            //we will need decode2 to make use of the hob
            stepNum = 2;
        }

        //load the accumulator from the x register
        if (IR == 0x8A)
        {
            accumulator = xReg;
            stepNum = 6;
        }

        //load the accumulator from the y register
        if (IR == 0x98)
        {
            accumulator = yReg;
            stepNum = 6;
        }

        //load x register with a constant
        if (IR == 0xA2)
        {
            //set x register to the MDR
            xReg = this.mmu.readImmediate(hexNum);
            programCounter++;
            stepNum = 6;
        }

        //load x register from the accumulator
        if (IR == 0xAA)
        {
            //set x register to the accumulator
            xReg = accumulator;
            stepNum = 6;
        }

        //load y register from the accumulator
        if (IR == 0xA8)
        {
            //set y register to the accumulator
            yReg = accumulator;
            stepNum = 6;
        }

        //no operation
        if (IR == 0xEA)
        {
            //does nothing and brings us back to fetch
            stepNum = 0;
        }

        //break
        if (IR == 0x00)
        {
            //kills the program
            process.kill(process.pid, 'SIGINT');
            stepNum = 0;
        }

        //branch
        if (IR == 0xD0)
        {
            //if zFlag is not set
            if (zFlag == 0x00)
            {
                //if the number represents a positive number
                if (this.mmu.readImmediate(hexNum) < 0x80)
                {
                    //add the MDR to the programCounter
                    programCounter = programCounter + this.mmu.combineBytes(this.mmu.readImmediate(hexNum), 0x00);
                }
                //if the number represents a negative number
                else
                {
                    //we need to make sure that there are two Fs in front since we are adding a one byte number
                    //to a two byte number and the MDR is a negative representation
                    programCounter = programCounter + this.mmu.combineBytes(this.mmu.readImmediate(hexNum), 0xFF);
                    //then find a way to remove the 1 in the front so that we are moving backwards
                    //and not moving extremely far forwards
                    if (programCounter > 0xFFFF)
                    {
                        programCounter = programCounter - 0x10000
                    }
                    
                }
                programCounter++;
                stepNum = 6
            }
            //branch fails because the zFlag is 1
            else
            {
                programCounter++;
                stepNum = 6;
            }
        }

        //System Calls
        if (IR == 0xFF)
        {
            //System Call 1
            if (xReg == 0x01)
            {
                //print out the value in the yReg
                process.stdout.write(yReg + " ");
                stepNum = 6;
            }
            //System Call 2
            if (xReg == 0x02)
            {

            }
            //System Call 3
            if (xReg == 0x03)
            {
                //we need to set the lob to the MDR and use decode2
                this.mmu.lob = this.mmu.readImmediate(hexNum);
                programCounter++;
                stepNum = 2;
            }
            //stepNum = 6
        }


        //console.log("decode");
        /*
        //if we need decode2
        stepNum = 2;
        //or go to execute
        stepNum = 3;
        */
    }

    decode2 (hexNum: number)
    {
        if (IR == 0xAD || IR == 0x8D || IR == 0xAE || IR == 0xAC || IR == 0x6D || IR == 0xEC || IR == 0xEE || IR == 0xFF)
        {
            //we need to set the hob to the MDR
            this.mmu.hob = this.mmu.readImmediate(hexNum);
            programCounter++;
            stepNum = 3;
        }
        //console.log("decode2");
    }

    execute ()
    {
        if (IR == 0xAD)
        {
            //set the accumulator to the combination of the data in the MAR lob and hob
            accumulator = this.mmu.readImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob));
            stepNum = 6;
        }
        if (IR == 0x8D)
        {
            //overwrite data in the MAR to the data in the MAR accumulator
            this.mmu.writeImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob), accumulator);
            stepNum = 6;
        }
        if (IR == 0xAE)
        {
            //set the xReg to the combination of the data in the MAR lob and hob
            xReg = this.mmu.readImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob));
            stepNum = 6;
        }
        if (IR == 0xAC)
        {
            //set the yReg to the combination of the data in the MAR lob and hob
            yReg = this.mmu.readImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob));
            stepNum = 6;
        }
        if (IR == 0x6D)
        {
            //add the data in the MAR combination of the lob and hob to the accumulator
            accumulator = accumulator + this.mmu.readImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob));
            stepNum = 6
        }
        if (IR == 0xEC)
        {
            //sets zFlag to 0 if the combination of the lob and hob does not equal the xReg
            if (this.mmu.readImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob)) != xReg)
            {
                zFlag = 0x00;
            }
            //sets zFlag to 1 if the combination of the lob and hob does equal the xReg
            else
            {
                zFlag = 0x01;
            }
            stepNum = 6;
        }
        if (IR == 0xEE)
        {
            //set the accumulator to the data in the MAR combination of the lob and hob
            accumulator = this.mmu.readImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob));
            //we need execute2
            stepNum = 4;
        }
        if (IR == 0xFF)
        {
            //set the tempPC to the current programCounter to remember where we were
            tempPC = programCounter;
            //set program counter to the combination of the lob and hob
            programCounter = this.mmu.combineBytes(this.mmu.lob, this.mmu.hob);
            //keep printing out ascii chars until we reach 0x00
            while (this.mmu.readImmediate(programCounter) != 0x00)
            {
                //make use of our ascii class to print out the byte from our MDR in character form
                process.stdout.write(this.ascii.byteToChar(this.mmu.readImmediate(programCounter)));
                programCounter++;
            }
            //set the programCounter to the tempPC to go back to where we were
            programCounter = tempPC;
            stepNum = 6;
        }

        //console.log("execute");
        /*
        //if we need execute2
        stepNum = 4
        */
    }

    execute2 ()
    {
        if (IR == 0xEE)
        {
            //increment the accumulator
            accumulator++;
            stepNum = 5;
        }
    }

    writeBack ()
    {
        if (IR == 0xEE)
        {
            //overwrite the data in the MAR combination of the lob and hob with the accumulator
            this.mmu.writeImmediate(this.mmu.combineBytes(this.mmu.lob, this.mmu.hob), accumulator);
            stepNum = 6;
        }
    }

    interruptCheck ()
    {
        //console.log("int");

        this.intController.interruptDequeue();
        //after its done it always needs to go back to fetch
        stepNum = 0;
    }

    constructor(cId: number, cName: string, cDebug: boolean) {
        super(cId, cName, cDebug);
            //this.mmu.memoryDump(0x0000, 0x000F);
        
    }
}
