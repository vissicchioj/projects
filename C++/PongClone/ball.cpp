#include "Ball.h"
#include <SFML/Graphics.hpp>

using namespace sf;

Texture textureTurnip;

// This the constructor function
Ball::Ball(float startX, float startY)
{
	m_Position.x = startX;
	m_Position.y = startY;

	textureTurnip.loadFromFile("graphics/turnip.png");
	spriteTurnip.setTexture(textureTurnip);

	spriteTurnip.setPosition(m_Position);
	//m_Shape.setSize(sf::Vector2f(10, 10));
	//m_Shape.setPosition(m_Position);
}

FloatRect Ball::getPosition()
{
	return spriteTurnip.getGlobalBounds();
}

Sprite Ball::getSprite()
{
	return spriteTurnip;
}

void Ball::setSpeed(float dS)
{
	m_Speed = m_Speed * dS;
}

void Ball::initSpeed()
{
	m_Speed = 1000.0f;
}

float Ball::getXVelocity()
{
	return m_DirectionX;
}

float Ball::getSpeed()
{
	return m_Speed;
}

void Ball::reboundSides()
{
	m_DirectionY = -m_DirectionY;
}

void Ball::reboundBatOrRight()
{
	m_DirectionX = -m_DirectionX;

}

void Ball::reboundLeft()
{
	m_Position.y = 200;
	m_Position.x = 1920 - 110;
	m_DirectionX = m_DirectionX;
}

void Ball::update(Time dt)
{
	// Update the ball position variables
	m_Position.y += m_DirectionY * m_Speed * dt.asSeconds();
	m_Position.x += -m_DirectionX * m_Speed * dt.asSeconds();

	m_Speed = m_Speed * 0.9999995;
	// Move the ball and the bat
	spriteTurnip.setPosition(m_Position);
}