#include "Bat.h"
#include <SFML/Graphics.hpp>

using namespace sf;

Texture textureShine;
Texture texturePower;

// This the constructor and it is called when we create an object
Bat::Bat(float startX, float startY)
{
	m_Position.x = startX;
	m_Position.y = startY;

	

	textureShine.loadFromFile("graphics/foxshine.png");
	spriteShine.setTexture(textureShine);
	texturePower.loadFromFile("graphics/foxShinePower.png");
	//spriteShine.setTexture(textureShine);

	spriteShine.setPosition(m_Position);

	//m_Shape.setSize(sf::Vector2f(5, 100));
	//m_Shape.setPosition(m_Position);


}

FloatRect Bat::getPosition()
{
	return spriteShine.getGlobalBounds();
	//return m_Shape.getGlobalBounds();
}

Sprite Bat::getSprite()
{
	// Sprite updates depending on powerup being active or not
	if (getPowerUp())
	{
		spriteShine.setTexture(texturePower);
	}
	else
	{
		spriteShine.setTexture(textureShine);
	}

	return spriteShine;
}

void Bat::setPowerUp(bool power)
{
	powerUp = power;
}

bool Bat::getPowerUp()
{
	return powerUp;
}

//RectangleShape Bat::getShape()
//{
	//return m_Shape;
//}


void Bat::update(Time dt)
{
	/*
	if (m_Position.y > 0 && m_Position.y < 1080 - 150)
	{
		if (playerMove == move::MOVEDOWN) {
			m_Position.y += m_Speed * dt.asSeconds();
		}
		if (playerMove == move::MOVEUP) {
			m_Position.y -= m_Speed * dt.asSeconds();
		}
	}
	else
	{
		if (m_Position.y > 1080 - 150)
		{
			m_Position.y = 1079 - 150;
		}
		else
		{
			m_Position.y = 1;
		}
	}
	*/

	if (playerMoveVert == moveVert::MOVEDOWN) 
	{
		if (m_Position.y < 1080 - 150)
		{
			m_Position.y += m_Speed * dt.asSeconds();
		}
	}
	if (playerMoveVert == moveVert::MOVEUP)
	{
		if (m_Position.y > 0)
		{
			m_Position.y -= m_Speed * dt.asSeconds();
		}
	}
	if (playerMoveHor == moveHor::MOVELEFT)
	{
		if (m_Position.x > 0)
		{
			m_Position.x -= m_Speed * dt.asSeconds();
		}
	}
	if (playerMoveHor == moveHor::MOVERIGHT)
	{
		if (m_Position.x < 300)
		{
			m_Position.x += m_Speed * dt.asSeconds();
		}
	}

	spriteShine.setPosition(m_Position);
	//m_Shape.setPosition(m_Position);
}