//interface to be used by Cpu and Memory
export interface ClockListener{
    // Notify all clock attached hardware when a pulse occurs
    pulse() : void

}