import { Hardware } from "./Hardware";

var asciiChar: string = "";
var asciiByte: number = 0;

export class Ascii extends Hardware{

    //takes a byte and returns the ascii character associated with that hex number
    //very useful for System Call when we want to print strings
    public byteToChar(byte: number)
    {
        if (byte == 0x41)
        {
            asciiChar = "A";
        }
        if (byte == 0x42)
        {
            asciiChar = "B";
        }
        if (byte == 0x43)
        {
            asciiChar = "C";
        }
        if (byte == 0x44)
        {
            asciiChar = "D";
        }
        if (byte == 0x45)
        {
            asciiChar = "E";
        }
        if (byte == 0x46)
        {
            asciiChar = "F";
        }
        if (byte == 0x47)
        {
            asciiChar = "G";
        }
        if (byte == 0x48)
        {
            asciiChar = "H";
        }
        if (byte == 0x49)
        {
            asciiChar = "I";
        }
        if (byte == 0x4A)
        {
            asciiChar = "J";
        }
        if (byte == 0x4B)
        {
            asciiChar = "K";
        }
        if (byte == 0x4C)
        {
            asciiChar = "L";
        }
        if (byte == 0x4D)
        {
            asciiChar = "M";
        }
        if (byte == 0x4E)
        {
            asciiChar = "N";
        }
        if (byte == 0x4F)
        {
            asciiChar = "O";
        }
        if (byte == 0x50)
        {
            asciiChar = "P";
        }
        if (byte == 0x51)
        {
            asciiChar = "Q";
        }
        if (byte == 0x52)
        {
            asciiChar = "R";
        }
        if (byte == 0x53)
        {
            asciiChar = "S";
        }
        if (byte == 0x54)
        {
            asciiChar = "T";
        }
        if (byte == 0x55)
        {
            asciiChar = "U";
        }
        if (byte == 0x56)
        {
            asciiChar = "V";
        }
        if (byte == 0x57)
        {
            asciiChar = "W";
        }
        if (byte == 0x58)
        {
            asciiChar = "X";
        }
        if (byte == 0x59)
        {
            asciiChar = "Y";
        }
        if (byte == 0x5A)
        {
            asciiChar = "Z";
        }
        if (byte == 0x61)
        {
            asciiChar = "a";
        }
        if (byte == 0x62)
        {
            asciiChar = "b";
        }
        if (byte == 0x63)
        {
            asciiChar = "c";
        }
        if (byte == 0x64)
        {
            asciiChar = "d";
        }
        if (byte == 0x65)
        {
            asciiChar = "e";
        }
        if (byte == 0x66)
        {
            asciiChar = "f";
        }
        if (byte == 0x67)
        {
            asciiChar = "g";
        }
        if (byte == 0x68)
        {
            asciiChar = "h";
        }
        if (byte == 0x69)
        {
            asciiChar = "i";
        }
        if (byte == 0x6A)
        {
            asciiChar = "j";
        }
        if (byte == 0x6B)
        {
            asciiChar = "k";
        }
        if (byte == 0x6C)
        {
            asciiChar = "l";
        }
        if (byte == 0x6D)
        {
            asciiChar = "m";
        }
        if (byte == 0x6E)
        {
            asciiChar = "n";
        }
        if (byte == 0x6F)
        {
            asciiChar = "o";
        }
        if (byte == 0x70)
        {
            asciiChar = "p";
        }
        if (byte == 0x71)
        {
            asciiChar = "q";
        }
        if (byte == 0x72)
        {
            asciiChar = "r";
        }
        if (byte == 0x73)
        {
            asciiChar = "s";
        }
        if (byte == 0x74)
        {
            asciiChar = "t";
        }
        if (byte == 0x75)
        {
            asciiChar = "u";
        }
        if (byte == 0x76)
        {
            asciiChar = "v";
        }
        if (byte == 0x77)
        {
            asciiChar = "w";
        }
        if (byte == 0x78)
        {
            asciiChar = "x";
        }
        if (byte == 0x79)
        {
            asciiChar = "y";
        }
        if (byte == 0x7A)
        {
            asciiChar = "z";
        }
        if (byte == 0x30)
        {
            asciiChar = "0";
        }
        if (byte == 0x31)
        {
            asciiChar = "1";
        }
        if (byte == 0x32)
        {
            asciiChar = "2";
        }
        if (byte == 0x33)
        {
            asciiChar = "3";
        }
        if (byte == 0x34)
        {
            asciiChar = "4";
        }
        if (byte == 0x35)
        {
            asciiChar = "5";
        }
        if (byte == 0x36)
        {
            asciiChar = "6";
        }
        if (byte == 0x37)
        {
            asciiChar = "7";
        }
        if (byte == 0x38)
        {
            asciiChar = "8";
        }
        if (byte == 0x39)
        {
            asciiChar = "9";
        }
        if (byte == 0x20)
        {
            asciiChar = " ";
        }
        if (byte == 0x21)
        {
            asciiChar = "!";
        }
        if (byte == 0x22)
        {
            asciiChar = '"';
        }
        if (byte == 0x23)
        {
            asciiChar = "#";
        }
        if (byte == 0x24)
        {
            asciiChar = "$";
        }
        if (byte == 0x25)
        {
            asciiChar = "%";
        }
        if (byte == 0x26)
        {
            asciiChar = "&";
        }
        if (byte == 0x27)
        {
            asciiChar = "'";
        }
        if (byte == 0x28)
        {
            asciiChar = "(";
        }
        if (byte == 0x29)
        {
            asciiChar = ")";
        }
        if (byte == 0x2A)
        {
            asciiChar = "*";
        }
        if (byte == 0x2B)
        {
            asciiChar = "+";
        }
        if (byte == 0x2C)
        {
            asciiChar = ",";
        }
        if (byte == 0x2D)
        {
            asciiChar = "-";
        }
        if (byte == 0x2E)
        {
            asciiChar = ".";
        }
        if (byte == 0x2F)
        {
            asciiChar = "/";
        }
        if (byte == 0x3A)
        {
            asciiChar = ":";
        }
        if (byte == 0x3B)
        {
            asciiChar = ";";
        }
        if (byte == 0x3C)
        {
            asciiChar = "<";
        }
        if (byte == 0x3D)
        {
            asciiChar = "=";
        }
        if (byte == 0x3E)
        {
            asciiChar = ">";
        }
        if (byte == 0x3F)
        {
            asciiChar = "?";
        }
        if (byte == 0x0A)
        {
            asciiChar = "\n";
        }
        
        return asciiChar;
    }

    
    //takes a character and returns the ascii hex number that represents that character
    public charToByte(char: string)
    {
        if (char == "A")
        {
            asciiByte = 0x41;
        }
        if (char == "B")
        {
            asciiByte = 0x42;
        }
        if (char == "C")
        {
            asciiByte = 0x43;
        }
        if (char == "D")
        {
            asciiByte = 0x44;
        }
        if (char == "E")
        {
            asciiByte = 0x45;
        }
        if (char == "F")
        {
            asciiByte = 0x46;
        }
        if (char == "G")
        {
            asciiByte = 0x47;
        }
        if (char == "H")
        {
            asciiByte = 0x48;
        }
        if (char == "I")
        {
            asciiByte = 0x49;
        }
        if (char == "J")
        {
            asciiByte = 0x4A;
        }
        if (char == "K")
        {
            asciiByte = 0x4B;
        }
        if (char == "L")
        {
            asciiByte = 0x4C;
        }
        if (char == "M")
        {
            asciiByte = 0x4D;
        }
        if (char == "N")
        {
            asciiByte = 0x4E;
        }
        if (char == "O")
        {
            asciiByte = 0x4F;
        }
        if (char == "P")
        {
            asciiByte = 0x50;
        }
        if (char == "Q")
        {
            asciiByte = 0x51;
        }
        if (char == "R")
        {
            asciiByte = 0x52;
        }
        if (char == "S")
        {
            asciiByte = 0x53;
        }
        if (char == "T")
        {
            asciiByte = 0x54;
        }
        if (char == "U")
        {
            asciiByte = 0x55;
        }
        if (char == "V")
        {
            asciiByte = 0x56;
        }
        if (char == "W")
        {
            asciiByte = 0x57;
        }
        if (char == "X")
        {
            asciiByte = 0x58;
        }
        if (char == "Y")
        {
            asciiByte = 0x59;
        }
        if (char == "Z")
        {
            asciiByte = 0x5A;
        }
        if (char == "a")
        {
            asciiByte = 0x61;
        }
        if (char == "b")
        {
            asciiByte = 0x62;
        }
        if (char == "c")
        {
            asciiByte = 0x63;
        }
        if (char == "d")
        {
            asciiByte = 0x64;
        }
        if (char == "e")
        {
            asciiByte = 0x65;
        }
        if (char == "f")
        {
            asciiByte = 0x66;
        }
        if (char == "g")
        {
            asciiByte = 0x67;
        }
        if (char == "h")
        {
            asciiByte = 0x68;
        }
        if (char == "i")
        {
            asciiByte = 0x69;
        }
        if (char == "j")
        {
            asciiByte = 0x6A;
        }
        if (char == "k")
        {
            asciiByte = 0x6B;
        }
        if (char == "l")
        {
            asciiByte = 0x6C;
        }
        if (char == "m")
        {
            asciiByte = 0x6D;
        }
        if (char == "n")
        {
            asciiByte = 0x6E;
        }
        if (char == "o")
        {
            asciiByte = 0x6F;
        }
        if (char == "p")
        {
            asciiByte = 0x70;
        }
        if (char == "q")
        {
            asciiByte = 0x71;
        }
        if (char == "r")
        {
            asciiByte = 0x72;
        }
        if (char == "s")
        {
            asciiByte = 0x73;
        }
        if (char == "t")
        {
            asciiByte = 0x74;
        }
        if (char == "u")
        {
            asciiByte = 0x75;
        }
        if (char == "v")
        {
            asciiByte = 0x76;
        }
        if (char == "w")
        {
            asciiByte = 0x77;
        }
        if (char == "x")
        {
            asciiByte = 0x78;
        }
        if (char == "y")
        {
            asciiByte = 0x79;
        }
        if (char == "z")
        {
            asciiByte = 0x7A;
        }
        if (char == "0")
        {
            asciiByte = 0x30;
        }
        if (char == "1")
        {
            asciiByte = 0x31;
        }
        if (char == "2")
        {
            asciiByte = 0x32;
        }
        if (char == "3")
        {
            asciiByte = 0x33;
        }
        if (char == "4")
        {
            asciiByte = 0x34;
        }
        if (char == "5")
        {
            asciiByte = 0x35;
        }
        if (char == "6")
        {
            asciiByte = 0x36;
        }
        if (char == "7")
        {
            asciiByte = 0x37;
        }
        if (char == "8")
        {
            asciiByte = 0x38;
        }
        if (char == "9")
        {
            asciiByte = 0x39;
        }
        if (char == " ")
        {
            asciiByte = 0x20;
        }
        if (char == "!")
        {
            asciiByte = 0x21;
        }
        if (char == '"')
        {
            asciiByte = 0x22;
        }
        if (char == "#")
        {
            asciiByte = 0x23;
        }
        if (char == "$")
        {
            asciiByte = 0x24;
        }
        if (char == "%")
        {
            asciiByte = 0x25;
        }
        if (char == "&")
        {
            asciiByte = 0x26;
        }
        if (char == "'")
        {
            asciiByte = 0x27;
        }
        if (char == "(")
        {
            asciiByte = 0x28;
        }
        if (char == ")")
        {
            asciiByte = 0x29;
        }
        if (char == "*")
        {
            asciiByte = 0x2A;
        }
        if (char == "+")
        {
            asciiByte = 0x2B;
        }
        if (char == ",")
        {
            asciiByte = 0x2C;
        }
        if (char == "-")
        {
            asciiByte = 0x2D;
        }
        if (char == ".")
        {
            asciiByte = 0x2E;
        }
        if (char == "/")
        {
            asciiByte = 0x2F;
        }
        if (char == ":")
        {
            asciiByte = 0x3A;
        }
        if (char == ";")
        {
            asciiByte = 0x3B;
        }
        if (char == "<")
        {
            asciiByte = 0x3C;
        }
        if (char == "=")
        {
            asciiByte = 0x3D;
        }
        if (char == ">")
        {
            asciiByte = 0x3E;
        }
        if (char == "?")
        {
            asciiByte = 0x3F;
        }
        if (char == "\n")
        {
            asciiByte = 0x0A;
        }

        return asciiByte;
    }
    

    constructor(aId: number, aName: string, aDebug: boolean){
        super(aId, aName, aDebug)

    }

}