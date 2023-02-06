// Pong.cpp : Defines the entry point for the console application.
//

#include "Bat.h"
#include "Ball.h"
#include <sstream>
#include <cstdlib>
#include <SFML/Graphics.hpp>


int main()
{
	// Game States
	enum class State { MENU, GAME_OVER, PLAYING };

	//bool powerUp = false;

	// Powerup has no cooldown on inital click
	bool initialClick = false;

	//Prevent additional bat hits
	bool batHit = true;

	// Create a video mode object
	VideoMode vm(1920, 1080);

	// Create and open a window for the game

	RenderWindow window(vm, "Pong", Style::Titlebar);

	int score = 0;
	int lives = 3;

	// Create a bat
	Bat bat(0 + 50, 1080/2);

	// Create a ball
	Ball ball(1920 - 110, 0 + 200);

	// Create a Text object called HUD
	Text hud;

	// A cool retro-style font
	Font font;
	font.loadFromFile("fonts/DS-DIGI.ttf");

	// Menu
	Text menuText;
	menuText.setFont(font);
	menuText.setCharacterSize(125);
	menuText.setFillColor(Color::White);
	menuText.setPosition(1920/2 - 500, 1080 * 0.75);
	menuText.setString("Press Enter to play!");

	Text titleText;
	titleText.setFont(font);
	titleText.setCharacterSize(125);
	titleText.setFillColor(Color::White);
	titleText.setPosition(1920 / 2 - 500, 1080 / 2);
	titleText.setString("Shine Pong");

	// Game Over
	Text gameOverText;
	gameOverText.setFont(font);
	gameOverText.setCharacterSize(125);
	gameOverText.setFillColor(Color::White);
	gameOverText.setPosition(1920/2 - 500, 1080/2);
	gameOverText.setString("GAME OVER!!!");

	// Set the font to our retro-style
	hud.setFont(font);

	// Make it nice and big
	hud.setCharacterSize(75);

	// Choose a color
	hud.setFillColor(Color::White);

	hud.setPosition(20, 20);

	// Here is our clock for timing everything
	Clock clock;

	Time total;
	Time cooldown;

	// Start with the MENU state
	State state = State::MENU;

	while (window.isOpen())
	{
		/*
		Handle the player input
		*********************************************************************
		*********************************************************************
		*********************************************************************
		*/
		Event event;
		while (window.pollEvent(event))
		{
			if (event.type == Event::KeyPressed)
			{
				if (event.key.code == Keyboard::Return &&
					state == State::MENU)
				{
					state = State::PLAYING;
					clock.restart();
					bat.setPowerUp(false);
					initialClick = false;
				}
				else if (event.key.code == Keyboard::Return &&
					state == State::GAME_OVER)
				{
					state = State::PLAYING;
					clock.restart();
					bat.setPowerUp(false);
					initialClick = false;
				}
			}
		}

		// Handle the player quitting
		if (Keyboard::isKeyPressed(Keyboard::Escape))
		{
			window.close();
		}


		if (state == State::PLAYING)
		{

			// Handle the pressing and releasing of the arrow keys
			if (Keyboard::isKeyPressed(Keyboard::Down) || Keyboard::isKeyPressed(Keyboard::Up))
			{
				if (Keyboard::isKeyPressed(Keyboard::Down))
				{
					bat.playerMoveVert = moveVert::MOVEDOWN;
				}
				else if (Keyboard::isKeyPressed(Keyboard::Up))
				{
					bat.playerMoveVert = moveVert::MOVEUP;
				}
			}
			else
			{
				bat.playerMoveVert = moveVert::STOPVERT;
			}

			if (Keyboard::isKeyPressed(Keyboard::Left) || Keyboard::isKeyPressed(Keyboard::Right))
			{
				if (Keyboard::isKeyPressed(Keyboard::Left))
				{
					bat.playerMoveHor = moveHor::MOVELEFT;
				}
				else if (Keyboard::isKeyPressed(Keyboard::Right))
				{
					bat.playerMoveHor = moveHor::MOVERIGHT;
				}
			}
			else
			{
				bat.playerMoveHor = moveHor::STOPHOR;
			}


			/*
			Update the bat, the ball and the HUD
			*********************************************************************
			*********************************************************************
			*********************************************************************
			*/
			// Update the delta time
			Time dt = clock.restart();
			total += dt;
			bat.update(dt);
			ball.update(dt);
			// Update the HUD text
			std::stringstream ss;
			ss << "Score:" << score << "    Lives:" << lives;
			hud.setString(ss.str());

			// Press space to enable power up
			if (Keyboard::isKeyPressed(Keyboard::Space))
			{
				// Initial powerup use doesn't have a cooldown
				if (initialClick == false)
				{
					bat.setPowerUp(true);
					initialClick = true;
					//cooldown = total;
				}
				else
				{
					// Now that it was used once, check for 6 second cooldown
					if (total.asSeconds() - cooldown.asSeconds() > 6)
					{
						bat.setPowerUp(true);
						//cooldown = total;
					}
				}
			}

			// Handle ball hitting the left or speed reaching 0
			if (ball.getPosition().left < 0 || ball.getSpeed() <= 1)
			{
				// reverse the ball direction
				ball.reboundLeft();
				ball.initSpeed();


				// Remove a life
				lives--;

				// Check for zero lives
				if (lives < 1) {
					state = State::GAME_OVER;
					// reset the score
					score = 0;
					// reset the lives
					lives = 3;
				}

			}

			// Handle ball hitting right
			if (ball.getPosition().left >= 1920 - 100)
			{
				ball.reboundBatOrRight();
				//ball.setSpeed(0.95);
				ball.setSpeed(0.99995);

				batHit = true;
				// Add a point to the players score
				score++;

			}

			// Handle ball hitting sides
			if (ball.getPosition().top < 0 ||
				ball.getPosition().top + 100 > window.getSize().y)
			{
				ball.reboundSides();
				ball.setSpeed(0.99995);
			}

			// Has the ball hit the bat?
			if (batHit == true)
			{
				
				if (ball.getPosition().intersects(bat.getPosition()))
				{
					// Hit detected so reverse the ball and score a point
					ball.reboundBatOrRight();
					if (bat.getPowerUp() == false)
					{
						ball.setSpeed(1.5);
					}
					else
					{
						ball.setSpeed(2);
						bat.setPowerUp(false);

						//reset powerup cooldown here to stop people from cheating and clicking it early to reuse for the next hit
						cooldown = total;
					}
					batHit = false;
				}
				
			}
		}
		

		/*
		Draw the bat, the ball and the HUD
		*********************************************************************
		*********************************************************************
		*********************************************************************
		*/
		window.clear();
		if (state == State::MENU)
		{
			window.draw(titleText);
			window.draw(menuText);
		}
		if (state == State::GAME_OVER)
		{
			window.draw(gameOverText);
			window.draw(menuText);
		}
		if (state == State::PLAYING)
		{
			window.draw(hud);
			window.draw(bat.getSprite());
			//window.draw(bat.getShape());
			window.draw(ball.getSprite());
		}
		window.display();
	}

	return 0;
}