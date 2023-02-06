import { Hardware } from "./Hardware";
import { Memory } from "./Memory";
import { Cpu } from "./Cpu";

var highOrder: string;
var lowOrder: string;
var parsedHex: number;

export class MMU extends Hardware{
    //mmu will be the middle-man of memory and cpu
    private mem: Memory = new Memory(0, "Memory", true);
    private cpu: Cpu = null;

    //will turn the number given into a String and then remove the first two digits (the high order byte)
    //and then turn the last two digits remaining (the low order byte) and turn it back into a number and return it
    setLowOrderByte(lob: number): number
    {
        lowOrder = lob.toString(16).toUpperCase();
        lowOrder = lowOrder.substr(2, lowOrder.length)
        parsedHex = Number(lowOrder);
        return parsedHex;
       
    }

    //will turn the number given into a String and then remove the last two digits (the low order byte)
    //and then turn the first two digits remaining (the high order byte) and turn it back into a number and return it
    setHighOrderByte(hob: number): number
    {
        highOrder = hob.toString(16).toUpperCase();
        highOrder = highOrder.substr(0, highOrder.length)
        parsedHex = Number(highOrder);
        return parsedHex;
    }

    //setting the MAR into the address given and the MDR to the data given
    //then makes use of write to set the memory array index specified the MAR to the MDR 
    writeImmediate(add: number, data: number)
    {
        this.mem.setMAR(add);
        this.mem.setMDR(data);
        this.mem.write();

    }

    //sets the MAR to the address given
    //then makes use of read to set the MDR to the memory array index specified in the MAR
    readImmediate(add: number)
    {
        this.mem.setMAR(add);
        this.mem.read();
        return this.mem.getMDR();
    }

    //displays the contents of memory from the starting address given to the final address given
    memoryDump(fromAddress: number, toAddress: number, debug: boolean, complete: boolean)
    {
        //Testing different programs
        /*
        this.writeImmediate(0x0000, 0xA9);
        this.writeImmediate(0x0001, 0x0D);
        this.writeImmediate(0x0002, 0xA9);
        this.writeImmediate(0x0003, 0x1D);
        this.writeImmediate(0x0004, 0xA9);
        this.writeImmediate(0x0005, 0x2D);
        this.writeImmediate(0x0006, 0xA9);
        this.writeImmediate(0x0007, 0x3F);
        this.writeImmediate(0x0008, 0xAD);
        this.writeImmediate(0x0009, 0x01);
        this.writeImmediate(0x000A, 0x00);
        this.writeImmediate(0x000B, 0xD0);
        this.writeImmediate(0x000C, 0x02);
        this.writeImmediate(0x000D, 0x00);
        */
        
        /*
        // load constant 0
        this.writeImmediate(0x0000, 0xA9);
        this.writeImmediate(0x0001, 0x00);
        // write acc (0) to 0040
        this.writeImmediate(0x0002, 0x8D);
        this.writeImmediate(0x0003, 0x40);
        this.writeImmediate(0x0004, 0x00);
        // load constant 1
        this.writeImmediate(0x0005, 0xA9);
        this.writeImmediate(0x0006, 0x01);
        // add acc (?) to mem 0040 (?)
        this.writeImmediate(0x0007, 0x6D);
        this.writeImmediate(0x0008, 0x40);
        this.writeImmediate(0x0009, 0x00);
        // write acc ? to 0040
this.writeImmediate(0x000A, 0x8D);
this.writeImmediate(0x000B, 0x40);
this.writeImmediate(0x000C, 0x00);
// Load y from memory 0040
this.writeImmediate(0x000D, 0xAC);
this.writeImmediate(0x000E, 0x40);
this.writeImmediate(0x000F, 0x00);
// Load x with constant (1) (to make the first system call)
this.writeImmediate(0x0010, 0xA2);
this.writeImmediate(0x0011, 0x01);
// make the system call to print the value in the y register (3)
this.writeImmediate(0x0012, 0xFF);
// Load x with constant (2) (to make the second system call for the string)
this.writeImmediate(0x0013, 0xA2);
this.writeImmediate(0x0014, 0x02);
// make the system call to print the value in the y register (3)
this.writeImmediate(0x0015, 0xFF);
this.writeImmediate(0x0016, 0x50);
this.writeImmediate(0x0017, 0x00);
// test DO (Branch Not Equal) will be NE and branch (0x0021 contains 0x20 and xReg contains B2)
this.writeImmediate(0x0018, 0xD0);
this.writeImmediate(0x0019, 0xED);
// globals
this.writeImmediate(0x0050, 0x2C);
this.writeImmediate(0x0052, 0x00);
*/

/*
this.writeImmediate(0x0000, 0xA9);
this.writeImmediate(0x0001, 0x05);
this.writeImmediate(0x0002, 0x8D);
this.writeImmediate(0x0003, 0x40);
this.writeImmediate(0x0004, 0x00);
this.writeImmediate(0x0005, 0xA9);
this.writeImmediate(0x0006, 0x01);
this.writeImmediate(0x0007, 0x8D);
this.writeImmediate(0x0008, 0x41);
this.writeImmediate(0x0009, 0x00);
this.writeImmediate(0x000A, 0xA8);
this.writeImmediate(0x000B, 0xA2);
this.writeImmediate(0x000C, 0x01);
this.writeImmediate(0x000D, 0xFF);
this.writeImmediate(0x000E, 0x6D);
this.writeImmediate(0x000F, 0x41);
this.writeImmediate(0x0010, 0x00);
this.writeImmediate(0x0011, 0xAA);
this.writeImmediate(0x0012, 0xEC);
this.writeImmediate(0x0013, 0x40);
this.writeImmediate(0x0014, 0x00);
this.writeImmediate(0x0015, 0xD0);
this.writeImmediate(0x0016, 0xF3);
this.writeImmediate(0x0017, 0x00);
*/
/*
// load constant 3
this.writeImmediate(0x0000, 0xA9);
this.writeImmediate(0x0001, 0x0A);
// write acc (3) to 0040
this.writeImmediate(0x0002, 0x8D);
this.writeImmediate(0x0003, 0x40);
this.writeImmediate(0x0004, 0x00);
// :loop
// Load y from memory (3)
this.writeImmediate(0x0005, 0xAC);
this.writeImmediate(0x0006, 0x40);
this.writeImmediate(0x0007, 0x00);
// Load x with constant (1) (to make the first system call)
this.writeImmediate(0x0008, 0xA2);
this.writeImmediate(0x0009, 0x01);
// make the system call to print the value in the y register (3)
this.writeImmediate(0x000A, 0xFF);
// Load x with constant (3) (to make the second system call for the string)
this.writeImmediate(0x000B, 0xA2);
this.writeImmediate(0x000C, 0x03);
// make the system call to print the value in the y register (3)
this.writeImmediate(0x000D, 0xFF);
this.writeImmediate(0x000E, 0x50);
this.writeImmediate(0x000F, 0x00);
// load the string
// 0A 48 65 6c 6c 6f 20 57 6f 72 6c 64 21
this.writeImmediate(0x0050, 0x0A);
this.writeImmediate(0x0051, 0x48);
this.writeImmediate(0x0052, 0x65);
this.writeImmediate(0x0053, 0x6C);
this.writeImmediate(0x0054, 0x6C);
this.writeImmediate(0x0055, 0x6F);
this.writeImmediate(0x0056, 0x20);
this.writeImmediate(0x0057, 0x57);
this.writeImmediate(0x0058, 0x6F);
this.writeImmediate(0x0059, 0x72);
this.writeImmediate(0x005A, 0x6C);
this.writeImmediate(0x005B, 0x64);
this.writeImmediate(0x005C, 0x21);
this.writeImmediate(0x005D, 0x0A);
this.writeImmediate(0x005E, 0x00);
*/

/*
My program from Lab 2
It makes use of a nested loop as well as ASCII characters which makes it a good test for my CPU
The output should be:
L
LL
LLL
Then it will break 
*/
this.writeImmediate(0x0000, 0xA9);
this.writeImmediate(0x0001, 0x04);
this.writeImmediate(0x0002, 0x8D);
this.writeImmediate(0x0003, 0x40);
this.writeImmediate(0x0004, 0x00);
this.writeImmediate(0x0005, 0xA9);
this.writeImmediate(0x0006, 0x01);
this.writeImmediate(0x0007, 0x8D);
this.writeImmediate(0x0008, 0x41);
this.writeImmediate(0x0009, 0x00);
this.writeImmediate(0x000A, 0xA9);
this.writeImmediate(0x000B, 0x01);
this.writeImmediate(0x000C, 0x8D);
this.writeImmediate(0x000D, 0x42);
this.writeImmediate(0x000E, 0x00);
//loop 2 will bring us back here 
this.writeImmediate(0x000F, 0xA9);
this.writeImmediate(0x0010, 0x00);
//loop 1 will bring us back here
this.writeImmediate(0x0011, 0xA8);
this.writeImmediate(0x0012, 0xA2);
this.writeImmediate(0x0013, 0x03);
//System Call to print the "L"
this.writeImmediate(0x0014, 0xFF);
this.writeImmediate(0x0015, 0x36);
this.writeImmediate(0x0016, 0x00);
this.writeImmediate(0x0017, 0x6D);
this.writeImmediate(0x0018, 0x41);
this.writeImmediate(0x0019, 0x00);
this.writeImmediate(0x001A, 0xAA);
this.writeImmediate(0x001B, 0xEC);
this.writeImmediate(0x001C, 0x42);
this.writeImmediate(0x001D, 0x00);
//loop 1
this.writeImmediate(0x001E, 0xD0);
this.writeImmediate(0x001F, 0xF1);
this.writeImmediate(0x0020, 0xAD);
this.writeImmediate(0x0021, 0x42);
this.writeImmediate(0x0022, 0x00);
this.writeImmediate(0x0023, 0xA8);
this.writeImmediate(0x0024, 0xA2);
this.writeImmediate(0x0025, 0x03);
//System Call to print a newLine
this.writeImmediate(0x0026, 0xFF);
this.writeImmediate(0x0027, 0x38);
this.writeImmediate(0x0028, 0x00);
this.writeImmediate(0x0029, 0x6D);
this.writeImmediate(0x002A, 0x41);
this.writeImmediate(0x002B, 0x00);
this.writeImmediate(0x002C, 0x8D);
this.writeImmediate(0x002D, 0x42);
this.writeImmediate(0x002E, 0x00);
this.writeImmediate(0x002F, 0xAA);
this.writeImmediate(0x0030, 0xEC);
this.writeImmediate(0x0031, 0x40);
this.writeImmediate(0x0032, 0x00);
//loop 2
this.writeImmediate(0x0033, 0xD0);
this.writeImmediate(0x0034, 0xDA);
//break
this.writeImmediate(0x0035, 0x00);
//ASCII bytes for System Call
//"L"
this.writeImmediate(0x0036, 0x4C);
this.writeImmediate(0x0037, 0x00);
//newLine
this.writeImmediate(0x0038, 0x0A);
this.writeImmediate(0x0039, 0x00);
this.writeImmediate(0x003A, 0x00);





        var i: number = fromAddress;
        if (debug == true)
        {
        this.log("Memory Dump: Debug");
        }
        this.log("--------------------------------------");
        for (i = fromAddress; i <= toAddress; i++)
        {
            this.readImmediate(i);
            this.log(this.hexLog(i, 4) + ":   |" + this.hexLog(this.mem.getMDR(), 2));
        }
        this.log("--------------------------------------");
        if (complete == true)
        {
        this.log("Memory Dump: Complete");
        }
    }

    lob: number = 0x00;
    hob: number = 0x00;
    combinedByte: number = 0x0000;
    byteFlipArray = [];

    //used to combine two bytes together in order to create a two byte number
    //this is used to implement little endian or to add to the programCounter as seen in branch
    combineBytes(loByte: number, hoByte: number): number
    {
        this.byteFlipArray[0] = hoByte;
        this.byteFlipArray[1] = loByte;
        this.combinedByte = ((this.byteFlipArray[0] << 8) | (this.byteFlipArray[1]));
        return this.combinedByte;


    }

    constructor(mmuId: number, mmuName: string, mmuDebug: boolean){
        super(mmuId, mmuName, mmuDebug)


    }
}
