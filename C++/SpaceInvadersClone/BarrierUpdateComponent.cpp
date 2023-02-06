#include "BarrierUpdateComponent.h"
#include "WorldState.h"

void BarrierUpdateComponent::update(float fps)
{

	// Update the collider
	m_RCC->setOrMoveCollider(m_TC->getLocation().x,
		m_TC->getLocation().y, m_TC->getSize().x,
		m_TC->getSize().y);

}
