#pragma once
#include "PlayableCharacter.h"

// Goes through fire

class Thomas : public PlayableCharacter
{
public:
	// A constructor specific to Thomas
	Thomas();

	// The overriden input handler for Thomas
	bool virtual handleInput();

};