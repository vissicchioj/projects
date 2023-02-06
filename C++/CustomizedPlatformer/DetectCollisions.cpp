#include "Engine.h"

/*
* Tile Types:
* Fire: Thomas can go through or respawn
* Water: Bob can go through or respawn
* Crazy Space: Respawn and affects your gravity until your next death
* Quick Sand: Respawn
* Chocolate: George can go through or respawn
* Teleporter: Changes George's position
*/

bool Engine::detectCollisions(PlayableCharacter& character)
{
	bool reachedGoal = false;
	// Make a rect for all his parts
	FloatRect detectionZone = character.getPosition();

	// Make a FloatRect to test each block
	FloatRect block;

	block.width = TILE_SIZE;
	block.height = TILE_SIZE;

	// Build a zone around thomas to detect collisions
	int startX = (int)(detectionZone.left / TILE_SIZE) - 1;
	int startY = (int)(detectionZone.top / TILE_SIZE) - 1;
	int endX = (int)(detectionZone.left / TILE_SIZE) + 2;

	// Thomas is quite tall so check a few tiles vertically
	int endY = (int)(detectionZone.top / TILE_SIZE) + 3;

	// Make sure we don't test positions lower than zero
	// Or higher than the end of the array
	if (startX < 0)startX = 0;
	if (startY < 0)startY = 0;
	if (endX >= m_LM.getLevelSize().x)
		endX = m_LM.getLevelSize().x;
	if (endY >= m_LM.getLevelSize().y)
		endY = m_LM.getLevelSize().y;

	// Has the character fallen out of the map?
	FloatRect level(0, 0, m_LM.getLevelSize().x * TILE_SIZE, m_LM.getLevelSize().y * TILE_SIZE);
	if (!character.getPosition().intersects(level))
	{
		if (m_LM.getCurrentLevel() >= 6)
		{
			// respawn the character
			character.spawn(m_LM.getStartPosition(), GRAVITY*2);
		}
		else
		{
			// respawn the character
			character.spawn(m_LM.getStartPosition(), GRAVITY);
		}
	}
	
	for (int x = startX; x < endX; x++)
	{
		for (int y = startY; y < endY; y++)
		{
			// Initialize the starting position of the current block
			block.left = x * TILE_SIZE;
			block.top = y * TILE_SIZE;

			// Has character been burnt or drowned?
			// Use head as this allows him to sink a bit
			if (m_ArrayLevel[y][x] == 2 || m_ArrayLevel[y][x] == 3 || m_ArrayLevel[y][x] == 5 || m_ArrayLevel[y][x] == 6 
				|| m_ArrayLevel[y][x] == 7 || m_ArrayLevel[y][x] == 8)
			{
				//if (character.getHead().intersects(block))
				//{
				if (m_Bob.getHead().intersects(block))
				{
					//character.spawn(m_LM.getStartPosition(), GRAVITY);
					// Which sound should be played?
					if (m_ArrayLevel[y][x] == 2)// Fire, ouch!
					{
						m_Bob.spawn(m_LM.getStartPosition(), GRAVITY);
						// Play a sound
						m_SM.playFallInFire();

					}
				}
				if (m_Thomas.getHead().intersects(block))
				{
					if (m_ArrayLevel[y][x] == 3) // Water
					{
						character.spawn(m_LM.getStartPosition(), GRAVITY);
						// Play a sound
						m_SM.playFallInWater();
					}
				}
				if (character.getHead().intersects(block))
				{ 
					if (m_ArrayLevel[y][x] == 5) // Crazy Space
					{
						// Changes gravity until next death, Tom and Bob will be lighter
						character.spawn(m_LM.getStartPosition(), 100.0);

						// Play a sound
						m_SM.playFallInCrazySpace();
					}
					if (m_LM.getCurrentLevel() >= 6)
					{
						if (m_ArrayLevel[y][x] == 6) // QuickSand
						{
							character.spawn(m_LM.getStartPosition(), GRAVITY * 2);
							// Play a sound
							// fall in sand maybe
							m_SM.playFallInQuickSand();
						}
					}
					else
					{
						if (m_ArrayLevel[y][x] == 6) // QuickSand
						{
							character.spawn(m_LM.getStartPosition(), GRAVITY);
							// Play a sound
							// fall in sand maybe
							m_SM.playFallInQuickSand();
						}
					}
				}
				if (m_Fido.getHead().intersects(block))
				{
					if (m_ArrayLevel[y][x] == 8) // Chocolate, George can eat but Fido cannot cuz he's a dog
					{
						m_Fido.spawn(m_LM.getStartPosition(), GRAVITY * 2);
						// Play a sound
						m_SM.playFallInQuickSand();
					}
				}

				if (m_George.getHead().intersects(block))
				{
					if (m_ArrayLevel[y][x] == 7) // Teleportation Device
					{
						Vector2f newPosition;
						// TODO: create the new position based on level 8
						// Perhaps add a sound for this and chocolate
						newPosition.x = 44*50;
						newPosition.y = 30*50;
						m_George.spawn(newPosition, 0);
						// Play a sound
						//m_SM.playFallInQuickSand();
					}
				}
			}


			// Is character colliding with a regular block
			if (m_ArrayLevel[y][x] == 1)
			{

				if (character.getRight().intersects(block))
				{
					character.stopRight(block.left);
				}
				else if (character.getLeft().intersects(block))
				{
					character.stopLeft(block.left);
				}


				if (character.getFeet().intersects(block))
				{
					character.stopFalling(block.top);
				}
				else if (character.getHead().intersects(block))
				{
					character.stopJump();
				}
			}

			// More collision detection here once we have learned about particle effects
			// Has the characters' feet touched fire or water?
			// If so, start a particle effect
			// Make sure this is the first time we have detected this
			// by seeing if an effect is already running			
			if (!m_PS.running()) {
				if (m_ArrayLevel[y][x] == 2 || m_ArrayLevel[y][x] == 3)
				{
					if (character.getFeet().intersects(block))
					{
						// position and start the particle system
						m_PS.emitParticles(character.getCenter());

					}
				}
			}
			if (!m_PS.runningCrazy()) {
				if (m_ArrayLevel[y][x] == 5 || m_ArrayLevel[y][x] == 7)
				{
					if (character.getFeet().intersects(block))
					{
						m_PS.emitParticlesCrazy(character.getCenter());
					}
				}
			}
			if (!m_PS.runningSand()) {
				if (m_ArrayLevel[y][x] == 6 || m_ArrayLevel[y][x] == 8)
				{
					if (character.getFeet().intersects(block))
					{
						m_PS.emitParticlesSand(character.getCenter());
					}
				}
			}

			// Has the character reached the goal?
			if (m_ArrayLevel[y][x] == 4)
			{
				// Character has reached the goal
				reachedGoal = true;
			}

		}

	}

	// All done, return, wheteher or not a new level might be required
	return reachedGoal;
}