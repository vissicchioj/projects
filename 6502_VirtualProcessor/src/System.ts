// import statements for hardware
import {Cpu} from "./hardware/Cpu";
import { Hardware } from "./hardware/Hardware";
import {Memory} from "./hardware/Memory";
import {Clock} from "./hardware/Clock";
import {MMU} from "./hardware/MMU";


//Variables
var mess: string;
var message: string;
var newId: number;
var newName: string;
var newDebug: boolean = true;

var sId: number = 0;
var sName: string = "System";
var sDebug: boolean = true;

var cId: number = 0;
var cName: string = "Cpu";
var cDebug: boolean = true;

var mId: number = 0;
var mName: string = "RAM";
var mDebug: boolean = true;

var clId: number = 0;
var clName: string = "CLK";
var clDebug: boolean = true;

var mmuId: number = 0;
var mmuName: string = "MMU";
var mmuDebug: boolean = true;

var messPassed: string = "Created";
var messMem: string = "- Adressable space: 65536";
var mmuMessPassed: string = "Initialized Memory";

//var hexTest: string = " ";

export class System extends Hardware{

    id: number;
    name: string;
    debug: boolean = true;
    message: string;
    
    private _CPU : Cpu = null;

    private _Memory : Memory = null;

    private _Clock : Clock = null;

    private _MMU : MMU = null;

    public running: boolean = false;

    constructor(sId: number, sName: string, sDebug: boolean) {
        
        super(sId, sName, sDebug);
        
        this.id = sId;
        this.name = sName;
        this.debug = sDebug

        super.id = this.id;
        super.name = this.name;
        super.debug = this.debug;
        
        
        /*
        Start the system (Analogous to pressing the power button and having voltages flow through the components)
        When power is applied to the system clock, it begins sending pulses to all clock observing hardware
        components so they can act on each clock cycle.
         */


        this.startSystem();

    }
    
    //logs out information when system starts
    public startSystem(): boolean {
        //console.log(hexTest.charCodeAt(0).toString(16));
        
        super.log(messPassed);
        //cDebug = false;
        this._CPU = new Cpu(cId, cName, cDebug);
        this._CPU.log(messPassed);
        this._Memory = new Memory(mId, mName, mDebug);
        this._Memory.log(messPassed + " " + messMem);
        this._Memory.displayMemory(0x14);
        this._MMU = new MMU(mmuId, mmuName, mmuDebug);
        /*
        //used to test lowOrder and highOrder
        console.log(this._MMU.setLowOrderByte(0x4455));
        console.log(this._MMU.setHighOrderByte(0x4455));
        */
        this._MMU.log(mmuMessPassed);
        //this._CPU.keyboard.monitorKeys();
        //this._MMU.memoryDump(0x0000, 0x000F);
        this._CPU.mmu.memoryDump(0x0000, 0x0039, true, true);
        this._CPU.intController.storeIO(this._CPU.keyboard, 0);
        this._Clock = new Clock(clId, clName, clDebug);
        this._Clock.log(messPassed);
        this._Clock.storeClListener(this._CPU, 0);
        this._Clock.storeClListener(this._Memory, 1);
        //this._Clock.interval;
        

        return true;
    }

    public stopSystem(): boolean {

        return false;

    }
}

let system: System = new System(sId, sName, sDebug);

