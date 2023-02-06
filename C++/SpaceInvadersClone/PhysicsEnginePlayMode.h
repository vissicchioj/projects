#pragma once
#include "GameObjectSharer.h"
#include "PlayerUpdateComponent.h"

class PhysicsEnginePlayMode
{
private:
	shared_ptr<PlayerUpdateComponent> m_PUC;

	GameObject* m_Player;
	GameObject* m_Barrier;
	bool m_InvaderHitWallThisFrame = false;
	bool m_InvaderHitWallPreviousFrame = false;
	bool m_MSHitWallThisFrame = false;
	bool m_MSHitWallPreviousFrame = false;
	bool m_NeedToDropDownAndReverse = false;
	bool m_CompletedDropDownAndReverse = false;
	bool m_NeedToReverse = false;
	bool m_CompletedReverse = false;


	void detectInvaderCollisions(
		vector<GameObject>& objects,
		const vector<int>& bulletPositions);

	
	void detectMotherShipCollisions(
		vector<GameObject>& objects,
		const vector<int>& bulletPositions);

	void handleMotherShipDirection();

	void detectBarrierCollisions(
		vector<GameObject>& objects,
		const vector<int>& bulletPositions);

		

	void detectPlayerCollisionsAndInvaderDirection(
		vector<GameObject>& objects,
		const vector<int>& bulletPositions);

	void handleInvaderDirection();

public:
	void initilize(GameObjectSharer& gos);
	void detectCollisions(
		vector<GameObject>& objects,
		const vector<int>& bulletPositions);
};