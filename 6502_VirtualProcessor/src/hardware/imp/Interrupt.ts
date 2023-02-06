
//interface to be used by interrupt driven hardware
export interface Interrupt{
    IRQ: number;
    priority: number;
    name: string;

    //input buffer
    inputBuffer?: String[];

    //output buffer
    outputBuffer?: String[];

}