import { Hardware } from "./Hardware";
import { ClockListener } from "./imp/ClockListener";

//variables
var i: number = 0x00;
var length: number = 0x10000;
var address: string;
var contains: string;

var memLog: boolean = false;



export class Memory extends Hardware implements ClockListener{
    //pulse method to implement the ClockListener interface
    pulse(){
        if (memLog == true)
        {
            this.log("recieved clock pulse");
        }
    }

    //reset all members in the array to be 0x00
    reset(){
        for(i = 0x00; i < length; i++){
            this.array[i] = 0x00;
        }
    }

    //create private MDR and MAR
    private myMDR: number = 0x00;
    private myMAR: number = 0x0000;

    //create getters and setters for the MAR and MDR
    public setMAR(newMAR: number)
    {
        this.myMAR = newMAR;
    }

    public setMDR(newMDR: number)
    {
        this.myMDR = newMDR
    }

    public getMAR()
    {
        return this.myMAR;
    }

    public getMDR()
    {
        return this.myMDR
    }

    //set the MDR to the memory array index specified in the MAR
    read()
    {
        this.setMDR(this.array[this.getMAR()]);
    }

    //set the memory array index specified in the MAR to the MDR 
    write()
    {
        this.array[this.getMAR()] = this.getMDR();
    }

    //create the private array of memory
    private array = [];

    constructor(mId: number, mName: string, mDebug: boolean){
        super(mId, mName, mDebug)
    
        //fills up the private array with 0x00
        for(i = 0x00; i < length; i++){
            this.array[i] = 0x00;
        }

    }


    //will make use of hexLog to display memory information
    public displayMemory(hexNum: number){
        for(i = 0x00; i < hexNum; i++)
        {
            address = this.hexLog(i, 4);
            contains = this.hexLog(this.array[i], 2);
            this.log(address + contains);
        }

    }
}
