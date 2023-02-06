#Jake Vissicchio
#This program is a text based one player fighting game with 3 options: punching, blocking, and grabbing.
#If both fighters grab each other it will perform a hug making the next winning action do extra damage.
#Your opponent will randomly choose options and you will try to beat them.
#This game is luck based


def main():
    from random import randint

    player_health = 10
    opponent_health = 10
    t = ["a", "b", "c"]

    opponent = t[randint(0, 2)]

    print("")
    print("Big Bout!")
    print("")
    name = input("What is your name?: ")
    print(name, "vs. Big Chief!")
    print("Controls:")
    print("Type A to punch")
    print("Type B to block")
    print("Type C to grab")

    while player_health > 0 and opponent_health > 0:
        player = input("Choose your action:")
        if player.lower() == "a":
            if opponent == "a":
                print("You both punch each other.")
                player_health = player_health - 2
                opponent_health = opponent_health - 2
                print(name,"'s health is", player_health)
                print("Big Chief's health is", opponent_health)
            elif opponent == "b":
                print("Big Chief blocked your punch and counter attacked.")
                player_health = player_health - 2
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
            elif opponent == "c":
                print("You punched Big Chief as he attempted to grab you.")
                opponent_health = opponent_health - 2
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
        elif player.lower() == "b":
            if opponent == "a":
                print("You block Big Chief's punch and you counter attack him")
                opponent_health = opponent_health - 2
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
            elif opponent == "b":
                print("Both you and Big Chief are blocking, nothing happens.")
                player_health = player_health + 0
                opponent_health = opponent_health + 0
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
            elif opponent == "c":
                print("You attempt to block but Big Chief grabs you and throws you.")
                player_health = player_health - 2
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
        elif player.lower() == "c":
            if opponent == "a":
                print("Big Chief punches you as you attempt to grab him.")
                player_health = player_health - 2
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
            elif opponent == "b":
                print("Big Chief attempts to block but you grab him and throw him.")
                opponent_health = opponent_health - 2
                print(name, "'s health is", player_health)
                print("Big Chief's health is", opponent_health)
            elif opponent == "c":
                print("You grab each other forcing a hug, try to win the next action for extra damage!")
                hug = input("Choose your action: ")
                if hug.lower() == "a":
                    if opponent == "a":
                        print("You both punch each other.")
                        player_health = player_health - 4
                        opponent_health = opponent_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                    elif opponent == "b":
                        print("Big Chief blocked your punch and counter attacked.")
                        player_health = player_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                    elif opponent == "c":
                        print("You punched Big Chief as he attempted to throw you.")
                        opponent_health = opponent_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                elif hug.lower() == "b":
                    if opponent == "a":
                        print("You block Big Chief's punch and you counter attack him")
                        opponent_health = opponent_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                    elif opponent == "b":
                        print("Both you and Big Chief are blocking and end up letting go of each other.")
                        player_health = player_health + 0
                        opponent_health = opponent_health + 0
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                    elif opponent == "c":
                        print("You attempt to block but Big Chief throws you.")
                        player_health = player_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                elif hug.lower() == "c":
                    if opponent == "a":
                        print("Big Chief punches you as you attempt to throw him.")
                        player_health = player_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                    elif opponent == "b":
                        print("Big Chief attempts to block but you throw him.")
                        opponent_health = opponent_health - 4
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)
                    elif opponent == "c":
                        print("You both attempt to throw then back off, nothing happens.")
                        player_health = player_health + 0
                        opponent_health = opponent_health + 0
                        print(name, "'s health is", player_health)
                        print("Big Chief's health is", opponent_health)

        opponent = t[randint(0, 2)]
    if player_health <= 0:
        print("KO!")
        print("You lose.")
        again = input("Would you like to play again? Yes or no?: ")
        if again.lower() == "yes":
            main()
        else:
            print("Thank you for playing!")
            exit()
    elif opponent_health <= 0:
        print("KO!")
        print("You win.")
        again = input("Would you like to play again? Yes or no?: ")
        if again.lower() == "yes":
            main()
        else:
            print("Thank you for playing!")
            exit()


main()