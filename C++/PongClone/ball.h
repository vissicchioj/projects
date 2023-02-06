#pragma once
#include <SFML/Graphics.hpp>

using namespace sf;

class Ball
{
private:
	Vector2f m_Position;
	//RectangleShape m_Shape;

	Sprite spriteTurnip;

	float m_Speed = 1000.0f;
	float m_DirectionX = .2f;
	float m_DirectionY = .2f;

public:
	Ball(float startX, float startY);

	FloatRect getPosition();

	//RectangleShape getShape();
	Sprite getSprite();

	void setSpeed(float dS);

	void initSpeed();

	float getXVelocity();

	float getSpeed();

	void reboundSides();

	void reboundBatOrRight();

	void reboundLeft();

	void update(Time dt);

};