#include <SFML/Graphics.hpp>
#include "ParticleSystem.h"

using namespace sf;
using namespace std;

void ParticleSystem::init(int numParticles)
{
	m_Vertices.setPrimitiveType(Points);
	m_Vertices.resize(numParticles);

	// Create the particles

	for (int i = 0; i < numParticles; i++)
	{
		srand(time(0) + i);
		float angle = (rand() % 360) * 3.14f / 180.f;
		float speed = (rand() % 600) + 600.f;

		Vector2f direction;

		direction = Vector2f(cos(angle) * speed,
			sin(angle) * speed);

		m_ParticleVectors[0].push_back(Particle(direction));

	}

	for (int i = 0; i < numParticles; i++)
	{
		srand(time(0) + i);
		float angle = (rand() % 360) * 3.14f / 180.f;
		float speed = (rand() % 600) + 600.f;

		Vector2f direction;

		direction = Vector2f(sin(angle) * speed,
			tan(angle) * speed);

		m_ParticleVectors[1].push_back(Particle(direction));

	}

	for (int i = 0; i < numParticles; i++)
	{
		srand(time(0) + i);
		float angle = (rand() % 360) * 3.14f / 180.f;
		float speed = (rand() % 600) + 600.f;

		Vector2f direction;

		direction = Vector2f(cos(angle) * speed,
			tan(angle) * speed);

		m_ParticleVectors[2].push_back(Particle(direction));

	}

}

void ParticleSystem::update(float dt, int currParticle)
{
	m_Duration -= dt;
	vector<Particle>::iterator i;
	int currentVertex = 0;
	
	for (i = m_ParticleVectors[currParticle].begin(); i != m_ParticleVectors[currParticle].end(); i++)
	{
		// Move the particle
		(*i).update(dt);

		// Update the vertex array
		m_Vertices[currentVertex].position = (*i).getPosition();

		// Move to the next vertex
		currentVertex++;
	}

	if (m_Duration < 0)
	{
		m_IsRunning = false;
		m_IsRunningCrazy = false;
		m_IsRunningSand = false;
	}

}

void ParticleSystem::emitParticles(Vector2f startPosition)
{
	m_IsRunning = true;
	m_Duration = 2;

	vector<Particle>::iterator i;
	int currentVertex = 0;

	for (i = m_ParticleVectors[0].begin(); i != m_ParticleVectors[0].end(); i++)
	{
		m_Vertices[currentVertex].color = Color::Yellow;
		(*i).setPosition(startPosition);

		currentVertex++;
	}

}

// Particle effect for crazy space
void ParticleSystem::emitParticlesCrazy(Vector2f startPosition)
{

	m_IsRunningCrazy = true;
	m_Duration = 2;

	vector<Particle>::iterator i;
	int currentVertex = 0;

	for (i = m_ParticleVectors[1].begin(); i != m_ParticleVectors[1].end(); i++)
	{
		m_Vertices[currentVertex].color = Color::Magenta;
		(*i).setPosition(startPosition);

		currentVertex++;
	}

}

// Particle effect for quick sand
void ParticleSystem::emitParticlesSand(Vector2f startPosition)
{

	m_IsRunningSand = true;
	m_Duration = 2;

	vector<Particle>::iterator i;
	int currentVertex = 0;

	for (i = m_ParticleVectors[2].begin(); i != m_ParticleVectors[2].end(); i++)
	{
		m_Vertices[currentVertex].color = Color::White;
		(*i).setPosition(startPosition);

		currentVertex++;
	}

}

void ParticleSystem::draw(RenderTarget& target, RenderStates states) const
{

	target.draw(m_Vertices, states);
}

bool ParticleSystem::running()
{
	return m_IsRunning;
}

bool ParticleSystem::runningCrazy()
{
	return m_IsRunningCrazy;
}

bool ParticleSystem::runningSand()
{
	return m_IsRunningSand;
}