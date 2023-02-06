
import {System} from "../System";
import {Cpu} from "./Cpu";
import {Memory} from "./Memory";

//variables 
var mess: string;
var message: string;
var newId: number;
var newName: string;
var newDebug: boolean = true;
var newMess: string;


export class Hardware {

    id: number;
    name: string;
    debug: boolean = true;
    message: string;
    

    //takes in an id, name, and a boolean in order to use information for other methods such as log
    constructor(idNum: number, theName: string, theDebug: boolean) {
        this.id = idNum;
        this.name = theName;
        this.debug = theDebug;
        

        
    }

    //logs information about the hardware and takes in a message to add at the end of the log
    public log(message: string){
        if (newDebug == true)
        {
        mess = "[HW - " + this.name + " id: " + this.id + " - " + Date.now() + "]: " + message;
        console.log(mess);
        //return mess;
        }
    }

    //hexLog takes in a number and a desired length while converting it into hex with padding zeroes 
    //while returning a new string to use for displayMemory
    public hexLog(hexNum: number, desLength: number)
    {
        var stHex: string = hexNum.toString(16).toUpperCase();
        for (let i = 0x00; i < desLength; i++)
        {
        if (desLength == 4)
        {
            if (stHex.length < 2)
            {
                stHex = "0" + stHex;
            }
            if (stHex.length < 3)
            {
                stHex = "0" + stHex;
            }
            if (stHex.length < 4)
            {
                stHex = "0" + stHex;
            }
            newMess = "Address: " + stHex;
        }
        else
        {
            if (stHex.length < 2)
            {
                stHex = "0" + stHex;
            }
            newMess = " Contains Value: " + stHex;
        }
        return newMess;
        }
    }

    public hexString(hexNum: number, desLength: number)
    {
        var stHex: string = hexNum.toString(16).toUpperCase();
        for (let i = 0x00; i < desLength; i++)
        {
        if (desLength == 4)
        {
            if (stHex.length < 2)
            {
                stHex = "0" + stHex;
            }
            if (stHex.length < 3)
            {
                stHex = "0" + stHex;
            }
            if (stHex.length < 4)
            {
                stHex = "0" + stHex;
            }
            newMess = stHex;
        }
        else
        {
            if (stHex.length < 2)
            {
                stHex = "0" + stHex;
            }
            newMess = stHex;
        }
        return newMess;
        }
    }
}
