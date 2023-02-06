import { Hardware } from "./Hardware";
import { Interrupt } from "./imp/Interrupt";

var i: number;
var length: number;

export class InterruptController extends Hardware{

    intGenerators: Interrupt[] = new Array(1);
    interruptsGenerated: string[];





    //Keep track of all hardware that generate interrupts
    //store keyboard
    public storeIO (intGenerator: Interrupt, pos: number){
        this.intGenerators[pos] = intGenerator;
    }

    //Keep track of all interrupts generated
    //?
    public acceptInterrupt (intGenerated: string){
        this.interruptsGenerated.push(intGenerated);
    }


    /*
    On each clock cycle the interrupt controller will provide
    the highest priority interrupt in the queue of waiting
    interrupts to the CPU. More detail on this in the coming
    steps.
    */
    
    public interruptDequeue()
    {
        try {
            this.intGenerators[0].outputBuffer.shift();
        } catch (error) {
            //console.log("no interrupts");
        }

    }




    constructor(icId: number, icName: string, icDebug: boolean){
        super(icId, icName, icDebug)


    }
}