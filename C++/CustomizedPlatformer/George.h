#pragma once
#include "PlayableCharacter.h"

// Can't fall or jump

class George : public PlayableCharacter
{
public:
	// A constructor specific to Bob
	George();

	// The overriden input handler for Bob
	bool virtual handleInput();

};