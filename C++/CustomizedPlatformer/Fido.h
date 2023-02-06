#pragma once
#include "PlayableCharacter.h"

// Jumps high and doubles gravity

class Fido : public PlayableCharacter
{
public:
	// A constructor specific to Fido
	Fido();

	// The overriden input handler for Fido
	bool virtual handleInput();

};