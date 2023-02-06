#pragma once

#include <SFML/Graphics.hpp>
using namespace sf;
using namespace std;

/*
 Level 1 & 6 are meant to be tutorial stages.
 Level 1 shows the hazard types as well and Thomas and Bob's movement.
 Level 7 shows the movement of the new characters, George and Fido.
*/

class LevelManager
{
private:
	Vector2i m_LevelSize;
	Vector2f m_StartPosition;
	float m_TimeModifier = 1;
	float m_BaseTimeLimit = 0;
	int m_CurrentLevel = 0;
	const int NUM_LEVELS = 8;

public:

	const int TILE_SIZE = 50;
	const int VERTS_IN_QUAD = 4;

	float getTimeLimit();

	Vector2f getStartPosition();

	int** nextLevel(VertexArray& rVaLevel);

	Vector2i getLevelSize();

	int getCurrentLevel();

};