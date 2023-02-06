import { Hardware } from "./Hardware";
import { ClockListener } from "./imp/ClockListener";
import {Cpu} from "./Cpu";
import {Memory} from "./Memory";

//variables
var i: number;

//switch to turn on and off the clockLog
var clockLogSwitch: boolean = false;


export class Clock extends Hardware{

    clockListeners: ClockListener[] = new Array(2);

    constructor(clId: number, clName: string, clDebug: boolean){
        super(clId, clName, clDebug)
        

    }

    //CPU stored within clockListeners[0] and Memory stored within clockListeners[1]
    public storeClListener (listener: ClockListener, pos: number){
        this.clockListeners[pos] = listener;
    }

    //displays the pulse information from the clock and the clockListeners
    public clockLog(){
        if (clockLogSwitch == true)
        {
            this.log("Clock Pulse Initialized");
        }//if
            for (i = 0; i < this.clockListeners.length; i++){
                this.clockListeners[i].pulse();
            }//for
    }

    //every 1000 miliseconds clockLog will be called
    interval = setInterval(() => {
        this.clockLog();
      }, 250);

}

