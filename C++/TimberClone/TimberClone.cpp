/*
* Shark Pit is a heavily modified version of the game Timber!!! as described in the textbook.
* In the game you play as a purple clownfish named Gup with a peculiar power. He can stop time when he stops
* swimming but unfortunately he can not maintain this ability for too long or else he dies. In a cruel
* twist of fate he found himself in a shark pit and it seems that all he can do is delay the inevitable.
* Take control of Gup and help him prolong his life as long as possible by making fast decisions and dodging sharks!
*/

// Include important C++ libraries here
#include <sstream>
#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>

// Make code easier to type with "using namespace"
using namespace sf;

// Function declaration
void updateSharks(int seed);

const int NUM_SHARKS = 6;
Sprite sharks[NUM_SHARKS];

// Where is the player/shark? 
// 3 Positions: Up, Middle, Down
enum class side { UP, MIDDLE, DOWN, NONE };
side sharkPositions[NUM_SHARKS];

int main()
{
	// Create a video mode object
	//VideoMode vm(1920, 1080);

	// Create and open a window for the game
	// Changed it to not be fullscreen
	//RenderWindow window(vm, "Timber!!!", Style::Titlebar);

	// Low res code
	VideoMode vm(960, 540);

	// Low res code
	//RenderWindow window(vm, "Shark Pit!");

	// Using a view allows SFML to smoothly scale our scene to fit
	View view(sf::FloatRect(0, 0, 1920, 1080));

	RenderWindow window(vm, "Shark Pit!", Style::Fullscreen);

	window.setView(view);
	//End of low res code!! Everything else is the same!!

	// Create a texture to hold a graphic on the GPU
	Texture textureBackground;

	// Load a graphic into the texture
	textureBackground.loadFromFile("graphics/underwaterBG.jpg");

	// Create a sprite
	Sprite spriteBackground;

	// Attach the texture to the sprite
	spriteBackground.setTexture(textureBackground);

	// Set the spriteBackground to cover the screen
	spriteBackground.setPosition(0, 0);

	// Prepare the goldfish
	Texture textureGoldfish;
	textureGoldfish.loadFromFile("graphics/goldfish.png");
	Sprite spriteGoldfish;
	spriteGoldfish.setTexture(textureGoldfish);
	spriteGoldfish.setPosition(0, 800);

	// Is the goldfish currently moving?
	bool goldfishActive = false;

	// flip goldfish
	bool goldfishLeft = true;
	bool goldfishRight = false;
	int goldfishDirection = 1;

	// How fast can the goldfish swim
	float goldfishSpeed = 0.0f;

	// make 3 bubble sprites from 1 texture
	Texture textureBubble;

	// Load 1 new texture //bubble needs to go vertical
	textureBubble.loadFromFile("graphics/bubble.png");

	// 3 New bubble sprites withe the same texture
	Sprite spriteBubble1;
	Sprite spriteBubble2;
	Sprite spriteBubble3;
	spriteBubble1.setTexture(textureBubble);
	spriteBubble2.setTexture(textureBubble);
	spriteBubble3.setTexture(textureBubble);

	// Position the bubbles off screen
	spriteBubble1.setPosition(0, 0);
	spriteBubble2.setPosition(0, 150);
	spriteBubble3.setPosition(0, 300);

	// Are the bubbles currently on screen?
	bool bubble1Active = false;
	bool bubble2Active = false;
	bool bubble3Active = false;

	// How fast is each bubble?
	float bubble1Speed = 0.0f;
	float bubble2Speed = 0.0f;
	float bubble3Speed = 0.0f;

	// Variables to control time itself
	Clock clock;

	// Time bar
	RectangleShape timeBar;
	float timeBarStartWidth = 400;
	float timeBarHeight = 80;
	timeBar.setSize(Vector2f(timeBarStartWidth, timeBarHeight));
	timeBar.setFillColor(Color::Red);
	timeBar.setPosition((1920 / 2) - timeBarStartWidth / 2, 980);

	Time gameTimeTotal;
	float timeRemaining = 6.0f;
	float timeBarWidthPerSecond = timeBarStartWidth / timeRemaining;

	// Track whether the game is running
	bool paused = true;

	// Draw some text
	int score = 0;

	sf::Text messageText;
	sf::Text scoreText;

	// We need to choose a font
	sf::Font font;
	font.loadFromFile("fonts/KOMIKAP_.ttf");

	// Set the font to our message
	messageText.setFont(font);
	scoreText.setFont(font);

	// Assign the actual message
	messageText.setString("Press Enter to start!");
	scoreText.setString("Score = 0");

	// Make it really big
	messageText.setCharacterSize(75);
	scoreText.setCharacterSize(100);

	// Choose a color
	messageText.setFillColor(Color::White);
	scoreText.setFillColor(Color::White);

	// Position the text
	FloatRect textRect = messageText.getLocalBounds();

	messageText.setOrigin(textRect.left +
		textRect.width / 2.0f,
		textRect.top +
		textRect.height / 2.0f);

	messageText.setPosition(1920 / 2.0f, 1080 / 2.0f);

	scoreText.setPosition(20, 20);

	// Prepare 5 sharks
	Texture textureShark;
	textureShark.loadFromFile("graphics/shark.png");

	// Set the texture for each shark sprite
	for (int i = 0; i < NUM_SHARKS; i++) {
		sharks[i].setTexture(textureShark);
		sharks[i].setPosition(-2000, -2000);
		sharks[i].setOrigin(220, 20);
	}

	// Prepare the player
	Texture texturePlayer;
	texturePlayer.loadFromFile("graphics/fish.png");
	Sprite spritePlayer;
	spritePlayer.setTexture(texturePlayer);
	spritePlayer.setPosition(250, 200);

	// The player starts on the top
	side playerSide = side::UP;

	// Prepare the gravestone
	Texture textureRIP;
	textureRIP.loadFromFile("graphics/rip.png");
	Sprite spriteRIP;
	spriteRIP.setTexture(textureRIP);
	spriteRIP.setPosition(600, 860);

	// Control the player input
	bool acceptInput = false;

	// Prepare the sound for moving
	SoundBuffer movBuffer;
	movBuffer.loadFromFile("sound/mov.wav");
	Sound mov;
	mov.setBuffer(movBuffer);

	// Prepare the sound of getting eaten
	SoundBuffer deathBuffer;
	deathBuffer.loadFromFile("sound/chomp.wav");
	Sound death;
	death.setBuffer(deathBuffer);

	// Prepare the sound of running out of time
	SoundBuffer ootBuffer;
	ootBuffer.loadFromFile("sound/tooslow.wav");
	Sound outOfTime;
	outOfTime.setBuffer(ootBuffer);

	// Game Loop
	while (window.isOpen())
	{
		// score ++;
		Event event;
		while (window.pollEvent(event))
		{


			if (event.type == Event::KeyReleased && !paused)
			{
				// Listen for key presses again
				acceptInput = true;
			}

		}

		/*
		****************************************
		Handle the players input
		****************************************
		*/

		if (Keyboard::isKeyPressed(Keyboard::Escape))
		{
			window.close();
		}

		// Start the game
		if (Keyboard::isKeyPressed(Keyboard::Return))
		{
			paused = false;

			// Reset the time and the score
			score = 0;
			timeRemaining = 6;

			// Make all the sharks disappear
			for (int i = 1; i < NUM_SHARKS; i++)
			{
				sharkPositions[i] = side::NONE;
			}

			// Make sure the gravestone is hidden
			spriteRIP.setPosition(675, 2000);

			// Move the player into position
			playerSide = side::UP;
			spritePlayer.setPosition(250, 200);

			acceptInput = true;


		}

		// Wrap the player controls to
		// Make sure we are accepting input
		if (acceptInput)
		{
			if (Keyboard::isKeyPressed(Keyboard::Right))
			{
				score++;

				// Add to the amount of time remaining
				timeRemaining += (2 / score) + .15;

				// update the sharks
				updateSharks(score);

				acceptInput = false;

				// Play a move sound
				mov.play();
			}
			// More code here next...
			// First handle pressing the right cursor key
			if (Keyboard::isKeyPressed(Keyboard::Down))
			{
				if (playerSide == side::UP)
				{
					// Make sure the player is on the right
					playerSide = side::MIDDLE;
					spritePlayer.setPosition(250, 500);
				}
				else if (playerSide == side::MIDDLE)
				{
					playerSide = side::DOWN;
					spritePlayer.setPosition(250, 800);
				}

				score++;

				// Add to the amount of time remaining
				timeRemaining += (2 / score) + .15;

				// update the sharks
				updateSharks(score);

				acceptInput = false;

				// Play a move sound
				mov.play();

			}

			// Handle the left cursor key
			if (Keyboard::isKeyPressed(Keyboard::Up))
			{
				if (playerSide == side::DOWN)
				{
					// Make sure the player is on the right
					playerSide = side::MIDDLE;
					spritePlayer.setPosition(250, 500);
				}
				else if (playerSide == side::MIDDLE)
				{
					playerSide = side::UP;
					spritePlayer.setPosition(250, 200);
				}

				score++;

				// Add to the amount of time remaining
				timeRemaining += (2 / score) + .15;

				// update the sharks
				updateSharks(score);

				acceptInput = false;

				// Play a move sound
				mov.play();

			}


		}


		/*
		****************************************
		Update the scene
		****************************************
		*/
		if (!paused)
		{

			// Measure time
			Time dt = clock.restart();

			// Subtract from the amount of time remaining
			timeRemaining -= dt.asSeconds();
			// size up the time bar
			timeBar.setSize(Vector2f(timeBarWidthPerSecond *
				timeRemaining, timeBarHeight));


			if (timeRemaining <= 0.0f) {

				// Pause the game
				paused = true;

				// Change the message shown to the player
				messageText.setString("Out of time!!");

				//Reposition the text based on its new size
				FloatRect textRect = messageText.getLocalBounds();
				messageText.setOrigin(textRect.left +
					textRect.width / 2.0f,
					textRect.top +
					textRect.height / 2.0f);

				messageText.setPosition(1920 / 2.0f, 1080 / 2.0f);

				// Play the out of time sound
				outOfTime.play();


			}


			// Setup the goldfish
			if (!goldfishActive)
			{
				if (goldfishLeft)
				{
					goldfishDirection = 1;
				}
				else
				{
					goldfishDirection = -1;
				}
				// How fast is the goldfish
				srand((int)time(0) * 10);
				goldfishSpeed = ((rand() % 200) + 200);

				// How high is the goldfish
				srand((int)time(0) * 10);
				float height = (rand() % 500) + 500;
				if (goldfishLeft)
				{
					spriteGoldfish.setPosition(2000, height);
				}
				else
				{
					spriteGoldfish.setPosition(-80, height);
				}
				goldfishActive = true;

			}
			else
				// Move the goldfish
			{
				if (goldfishLeft)
				{
					spriteGoldfish.setPosition(
						(spriteGoldfish.getPosition().x -
							(goldfishSpeed * dt.asSeconds())),
						spriteGoldfish.getPosition().y);
				}
				else
				{
					spriteGoldfish.setPosition(
						(spriteGoldfish.getPosition().x +
							(goldfishSpeed * dt.asSeconds())),
						spriteGoldfish.getPosition().y);
				}

				// Has the goldfish reached the right hand edge of the screen?
				if (spriteGoldfish.getPosition().x < -100 || spriteGoldfish.getPosition().x > 2200)
				{
					// Alternates booleans 
					goldfishActive = false;
					if (goldfishLeft)
					{
						goldfishLeft = false;
						goldfishRight = true;
					}
					else
					{
						goldfishLeft = true;
						goldfishRight = false;
					}
					// Resource I found to flip sprites using SFML
					// https://en.sfml-dev.org/forums/index.php?topic=7572.msg49979#msg49979
					spriteGoldfish.scale(-1.f, 1.f);

				}
			}

			// Manage the bubbles
			// Bubble 1
			if (!bubble1Active)
			{

				// How fast is the bubble
				srand((int)time(0) * 10);
				bubble1Speed = 150;

				// How high is the bubble
				srand((int)time(0) * 10);
				float width = (rand() % 150);
				spriteBubble1.setPosition(width, 1080);
				bubble1Active = true;


			}
			else
			{

				spriteBubble1.setPosition(
					spriteBubble1.getPosition().x,
					spriteBubble1.getPosition().y
					- (bubble1Speed * dt.asSeconds()));

				// Has the bubble reached the right hand edge of the screen?
				if (spriteBubble1.getPosition().y < 0)
				{
					// Set it up ready to be a whole new bubble next frame
					bubble1Active = false;
				}
			}
			// Bubble 2
			if (!bubble2Active)
			{

				// How fast is the bubble
				srand((int)time(0) * 20);
				bubble2Speed = 110;

				// How high is the bubble
				srand((int)time(0) * 20);
				float width = (rand() % 300) + 400;
				spriteBubble2.setPosition(width, 1080);
				bubble2Active = true;


			}
			else
			{

				spriteBubble2.setPosition(
					spriteBubble2.getPosition().x,
					spriteBubble2.getPosition().y -
					(bubble2Speed* dt.asSeconds()));

				// Has the bubble reached the right hand edge of the screen?
				if (spriteBubble2.getPosition().y < 0)
				{
					// Set it up ready to be a whole new bubble next frame
					bubble2Active = false;
				}
			}

			if (!bubble3Active)
			{

				// How fast is the bubble
				srand((int)time(0) * 30);
				bubble3Speed = 70;

				// How high is the bubble
				srand((int)time(0) * 30);
				float width = (rand() % 450) + 700;
				spriteBubble3.setPosition(width, 1080);
				bubble3Active = true;


			}
			else
			{

				spriteBubble3.setPosition(
					spriteBubble3.getPosition().x,
					spriteBubble3.getPosition().y -
					(bubble3Speed * dt.asSeconds()));

				// Has the bubble reached the right hand edge of the screen?
				if (spriteBubble3.getPosition().y < 0)
				{
					// Set it up ready to be a whole new bubble next frame
					bubble3Active = false;
				}
			}

			// Update the score text
			std::stringstream ss;
			ss << "Score = " << score;
			scoreText.setString(ss.str());

			// update the shark sprites
			for (int i = 0; i < NUM_SHARKS; i++)
			{
				// Sharks start on the right side of the screen then move towards the left side
				float width = 1920;
				width -= i * 322;

				if (sharkPositions[i] == side::UP)
				{
					// Move the sprite to the up side
					sharks[i].setPosition(width, 200);

				}
				else if (sharkPositions[i] == side::DOWN)
				{
					// Move the sprite to the down side
					sharks[i].setPosition(width, 800);

				}
				else if (sharkPositions[i] == side::MIDDLE)
				{
					// Move the sprite to the middle side
					sharks[i].setPosition(width, 500);


				}
				else
				{
					// Hide the shark
					sharks[i].setPosition(width, 5000);
				}
			}

			// has the player been eaten by a shark?
			if (sharkPositions[5] == playerSide)
			{
				// death
				paused = true;
				acceptInput = false;

				// hide the player
				spritePlayer.setPosition(2000, 660);

				// Change the text of the message
				messageText.setString("EATEN!!");

				// Center it on the screen
				FloatRect textRect = messageText.getLocalBounds();

				messageText.setOrigin(textRect.left +
					textRect.width / 2.0f,
					textRect.top + textRect.height / 2.0f);

				messageText.setPosition(1920 / 2.0f,
					1080 / 2.0f);

				// Play the death sound
				death.play();


			}


		}// End if(!paused)

		 /*
		 ****************************************
		 Draw the scene
		 ****************************************
		 */

		 // Clear everything from the last frame
		window.clear();

		// Draw our game scene here
		window.draw(spriteBackground);

		// Draw the bubbles
		window.draw(spriteBubble1);
		window.draw(spriteBubble2);
		window.draw(spriteBubble3);

		// Draw the sharks
		for (int i = 0; i < NUM_SHARKS; i++) {
			window.draw(sharks[i]);
		}

		// Draw the player
		window.draw(spritePlayer);

		// Draw the goldfish
		window.draw(spriteGoldfish);

		// Draw the score
		window.draw(scoreText);

		// Draw the timebar
		window.draw(timeBar);


		if (paused)
		{
			// Draw our message
			window.draw(messageText);
		}

		// Show everything we just drew
		window.display();


	}

	return 0;
}

// Function definition
void updateSharks(int seed)
{
	// Move all the sharks down one place
	for (int j = NUM_SHARKS - 1; j > 0; j--) {
		sharkPositions[j] = sharkPositions[j - 1];
	}

	// Spawn a new shark at position 0
	// UP, MIDDLE, DOWN or NONE
	srand((int)time(0) + seed);
	int r = (rand() % 5);

	switch (r) {
	case 0:
		sharkPositions[0] = side::UP;
		break;

	case 1:
		sharkPositions[0] = side::MIDDLE;
		break;

	case 2:
		sharkPositions[0] = side::DOWN;
		break;

	default:
		sharkPositions[0] = side::NONE;
		break;
	}


}