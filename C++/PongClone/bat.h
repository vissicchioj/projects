#pragma once
#include <SFML/Graphics.hpp>

using namespace sf;

enum class move { MOVEUP, MOVEDOWN, MOVELEFT, MOVERIGHT, STOP };

enum class moveVert { MOVEUP, MOVEDOWN, STOPVERT };
enum class moveHor {MOVELEFT, MOVERIGHT, STOPHOR };

class Bat
{
private:
	Vector2f m_Position;

	// A RectangleShape object
	RectangleShape m_Shape;
	Sprite spriteShine;


	float m_Speed = 1000.0f;

	bool powerUp = false;

	//bool m_MovingRight = false;
	//bool m_MovingLeft = false;


public:
	Bat(float startX, float startY);

	FloatRect getPosition();

	//FloatRect getRect();
	RectangleShape getShape();

	Sprite getSprite();

	void setPowerUp(bool power);
	bool getPowerUp();

	//enum class move {MOVEUP, MOVEDOWN, STOP};
	moveVert playerMoveVert = moveVert::STOPVERT;
	moveHor playerMoveHor = moveHor::STOPHOR;

	void update(Time dt);

};