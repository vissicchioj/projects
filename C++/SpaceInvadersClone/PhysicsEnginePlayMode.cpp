#include "DevelopState.h"
#include "PhysicsEnginePlayMode.h"
#include <iostream>
#include "SoundEngine.h"
#include "WorldState.h"
#include "InvaderUpdateComponent.h"
#include "MotherShipUpdateComponent.h"
#include "BulletUpdateComponent.h"




void PhysicsEnginePlayMode::detectInvaderCollisions(
	vector<GameObject>& objects,
	const vector<int>& bulletPositions)
{
	Vector2f offScreen(-1, -1);

	auto invaderIt = objects.begin();
	auto invaderEnd = objects.end();
	for (invaderIt;
		invaderIt != invaderEnd;
		++invaderIt)
	{
		if ((*invaderIt).isActive()
			&& (*invaderIt).getTag() == "invader")
		{
			auto bulletIt = objects.begin();
			// Jump to the first bullet
			advance(bulletIt, bulletPositions[0]);
			auto bulletEnd = objects.end();
			for (bulletIt;
				bulletIt != bulletEnd;
				++bulletIt)

			{
				if ((*invaderIt).getEncompassingRectCollider()
					.intersects((*bulletIt)
						.getEncompassingRectCollider())
					&& (*bulletIt).getTag() == "bullet"
					&& static_pointer_cast<BulletUpdateComponent>(
						(*bulletIt).getFirstUpdateComponent())
					->m_BelongsToPlayer)
				{
					SoundEngine::playInvaderExplode();
					(*invaderIt).getTransformComponent()
						->getLocation() = offScreen;

					(*bulletIt).getTransformComponent()
						->getLocation() = offScreen;

					WorldState::SCORE++;
					WorldState::NUM_INVADERS--;
					(*invaderIt).setInactive();
				}
			}
		}
	}
}


void PhysicsEnginePlayMode::detectMotherShipCollisions(
	vector<GameObject>& objects,
	const vector<int>& bulletPositions)
{
	Vector2f offScreen(-1, -1);

	auto invaderIt = objects.begin();
	auto invaderEnd = objects.end();
	for (invaderIt;
		invaderIt != invaderEnd;
		++invaderIt)
	{
		if ((*invaderIt).isActive()
			&& (*invaderIt).getTag() == "mothership")
		{
			auto bulletIt = objects.begin();
			// Jump to the first bullet
			advance(bulletIt, bulletPositions[0]);
			auto bulletEnd = objects.end();
			for (bulletIt;
				bulletIt != bulletEnd;
				++bulletIt)

			{
				if ((*invaderIt).getEncompassingRectCollider()
					.intersects((*bulletIt)
						.getEncompassingRectCollider())
					&& (*bulletIt).getTag() == "bullet"
					&& static_pointer_cast<BulletUpdateComponent>(
						(*bulletIt).getFirstUpdateComponent())
					->m_BelongsToPlayer)
				{
					SoundEngine::playInvaderExplode();
					(*invaderIt).getTransformComponent()
						->getLocation() = offScreen;

					(*bulletIt).getTransformComponent()
						->getLocation() = offScreen;

					WorldState::SCORE = WorldState::SCORE + 10;
					(*invaderIt).setInactive();
				}
			}
		}
	}
}

void PhysicsEnginePlayMode::detectBarrierCollisions(
	vector<GameObject>& objects,
	const vector<int>& bulletPositions)
{
	Vector2f offScreen(-1, -1);

	auto invaderIt = objects.begin();
	auto invaderEnd = objects.end();
	for (invaderIt;
		invaderIt != invaderEnd;
		++invaderIt)
	{
		if ((*invaderIt).isActive()
			&& (*invaderIt).getTag() == "barrier")
		{
			auto bulletIt = objects.begin();
			// Jump to the first bullet
			advance(bulletIt, bulletPositions[0]);
			auto bulletEnd = objects.end();
			for (bulletIt;
				bulletIt != bulletEnd;
				++bulletIt)

			{
				if ((*invaderIt).getEncompassingRectCollider()
					.intersects((*bulletIt)
						.getEncompassingRectCollider())
					&& (*bulletIt).getTag() == "bullet"
					&& static_pointer_cast<BulletUpdateComponent>(
						(*bulletIt).getFirstUpdateComponent())
					->m_BelongsToInvader)
				{
					//SoundEngine::playInvaderExplode();
					WorldState::BARRIERHEALTH--;
					if (WorldState::BARRIERHEALTH < 1)
					{
						(*invaderIt).getTransformComponent()
							->getLocation() = offScreen;
						(*invaderIt).setInactive();
					}

					(*bulletIt).getTransformComponent()
						->getLocation() = offScreen;

					
				}
			}
		}
	}
}


void PhysicsEnginePlayMode::
detectPlayerCollisionsAndInvaderDirection(
	vector<GameObject>& objects,
	const vector<int>& bulletPositions)
{
	Vector2f offScreen(-1, -1);

	FloatRect playerCollider =
		m_Player->getEncompassingRectCollider();

	shared_ptr<TransformComponent> playerTransform =
		m_Player->getTransformComponent();

	Vector2f playerLocation =
		playerTransform->getLocation();

	auto it3 = objects.begin();
	auto end3 = objects.end();
	for (it3;
		it3 != end3;
		++it3)
	{
		if ((*it3).isActive() &&
			(*it3).hasCollider() &&
			(*it3).getTag() != "Player")
		{
			// Get a reference to all the parts of 
			// the current game object we might need
			FloatRect currentCollider = (*it3)
				.getEncompassingRectCollider();

			// Detect collisions between objects with the player
			if (currentCollider.intersects(playerCollider))
			{
				if ((*it3).getTag() == "bullet")
				{
					SoundEngine::playPlayerExplode();
					WorldState::LIVES--;
					(*it3).getTransformComponent()->
						getLocation() = offScreen;
				}

				if ((*it3).getTag() == "invader")
				{
					SoundEngine::playPlayerExplode();
					SoundEngine::playInvaderExplode();
					WorldState::LIVES--;
					(*it3).getTransformComponent()->
						getLocation() = offScreen;

					WorldState::SCORE++;
					(*it3).setInactive();
				}
			}

			shared_ptr<TransformComponent>
				currentTransform =
				(*it3).getTransformComponent();

			Vector2f currentLocation =
				currentTransform->getLocation();

			string currentTag = (*it3).getTag();
			Vector2f currentSize =
				currentTransform->getSize();

			// Handle the direction and descent of the invaders
			if (currentTag == "invader")
			{
				// This is an invader
				if (!m_NeedToDropDownAndReverse &&
					!m_InvaderHitWallThisFrame)
				{
					// Currently no need to dropdown 
					// and reverse from previous frame 
					// or any hits this frame
					if (currentLocation.x >=
						WorldState::WORLD_WIDTH - currentSize.x)
					{
						// The invader is passed its furthest right position
						if (static_pointer_cast
							<InvaderUpdateComponent>((*it3)
								.getFirstUpdateComponent())->
							isMovingRight())
						{
							// The invader is travelling right so set 
							// a flag that an invader has collided 
							m_InvaderHitWallThisFrame = true;
						}
					}
					else if (currentLocation.x < 0)
					{
						// The invader is past its furthest left position
						if (!static_pointer_cast<InvaderUpdateComponent>(
							(*it3).getFirstUpdateComponent())->isMovingRight())
						{
							// The invader is travelling left so 
							// set a flag that an invader has collided 
							m_InvaderHitWallThisFrame = true;
						}
					}
				}
				else if (m_NeedToDropDownAndReverse
					&& !m_InvaderHitWallPreviousFrame)
				{
					// Drop down and reverse has been set
					if ((*it3).hasUpdateComponent())
					{
						// Drop down and reverse
						static_pointer_cast<InvaderUpdateComponent>(
							(*it3).getFirstUpdateComponent())
							->dropDownAndReverse();
					}
				}
			}
			// Handle the direction of mothership

			else if (currentTag == "mothership")
			{
				// This is an invader
				if (!m_NeedToReverse &&
					!m_MSHitWallThisFrame)
				{
					// Currently no need to dropdown 
					// and reverse from previous frame 
					// or any hits this frame
					if (currentLocation.x >=
						WorldState::WORLD_WIDTH - currentSize.x )
					{
						// The invader is passed its furthest right position
						if (static_pointer_cast
							<MotherShipUpdateComponent>((*it3)
								.getFirstUpdateComponent())->
							isMovingRight())
						{
							// The invader is travelling right so set 
							// a flag that an invader has collided 
							m_MSHitWallThisFrame = true;
						}
					}
					/*
					else if (currentLocation.x < 0)
					{
						// The invader is past its furthest left position
						if (!static_pointer_cast<MotherShipUpdateComponent>(
							(*it3).getFirstUpdateComponent())->isMovingRight())
						{
							// The invader is travelling left so 
							// set a flag that an invader has collided 
							m_MSHitWallThisFrame = true;
						}
					}
					*/
				}
				else if (m_NeedToReverse
					 && !m_MSHitWallPreviousFrame)
				{
					// Drop down and reverse has been set
					if ((*it3).hasUpdateComponent())
					{
						// Drop down and reverse
						static_pointer_cast<MotherShipUpdateComponent>(
							(*it3).getFirstUpdateComponent())
							->reverse();
					}
				}
			}
		}
	}
}



void PhysicsEnginePlayMode::handleInvaderDirection()
{
	if (m_InvaderHitWallThisFrame) {
		m_NeedToDropDownAndReverse = true;
		m_InvaderHitWallThisFrame = false;
	}
	else {
		m_NeedToDropDownAndReverse = false;
	}
}

void PhysicsEnginePlayMode::handleMotherShipDirection()
{
	if (m_MSHitWallThisFrame) {
		m_NeedToReverse = true;
	}
	else {
		m_NeedToReverse = false;
	}
}

void PhysicsEnginePlayMode::initilize(GameObjectSharer& gos) {
	m_PUC = static_pointer_cast<PlayerUpdateComponent>(
		gos.findFirstObjectWithTag("Player")
		.getComponentByTypeAndSpecificType("update", "player"));

	m_Player = &gos.findFirstObjectWithTag("Player");
}

void PhysicsEnginePlayMode::detectCollisions(
	vector<GameObject>& objects,
	const vector<int>& bulletPositions)
{
	detectBarrierCollisions(objects, bulletPositions);
	detectInvaderCollisions(objects, bulletPositions);
	detectMotherShipCollisions(objects, bulletPositions);
	detectPlayerCollisionsAndInvaderDirection(
		objects, bulletPositions);

	handleInvaderDirection();
	handleMotherShipDirection();
}