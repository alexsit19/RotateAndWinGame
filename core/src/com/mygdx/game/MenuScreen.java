package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

public class MenuScreen implements Screen {

    private MainGame game;
    private SpriteBatch batch;
    private Texture menuTexture, blackTexture;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Sprite titleSprite, settingsButtonSprite, exitButtonSprite,
            startButtonSprite, helpButtonSprite, achieveButtonSprite;


    private SpriteDrawable startButtonDrawable, settingsButtonDrawable, exitButtonDrawable,
            achieveButtonDrawable, helpButtonDrawable;

    private ImageButton exitImageButton, startImageButton, settingsImageButton,
            achieveImageButton, helpImageButton;

    private float height, width, scaleTitle, scaleButtonStart, scaleOtherButtons;
    private float startButtonWidth, startButtonHeight, otherButtonWidth, otherButtonHeight;
    private float titleSpriteY, titleSpriteX;
    private float startButtonX, startButtonY;
    private float settingsButtonX, settingsButtonY;
    private float helpButtonX, helpButtonY;
    private float exitButtonX, exitButtonY;
    private float achieveButtonX, achieveButtonY;
    private Stage stage;
    private Window exitWindow;
    private Skin skin;
    private TextButton yesButton, noButton;
    private Label exitText;
    private Viewport viewport;
    private Image blackScreenImage;


    public MenuScreen(final MainGame game){
        this.game = game;
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();


        blackTexture = new Texture("blackScreenImage.png");
        backgroundTexture = new Texture("1680x1050/background_7.png");

        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setCenter(backgroundSprite.getWidth()/2, backgroundSprite.getHeight()/2);
        backgroundSprite.setSize(width, height);

        skin = new Skin(Gdx.files.internal("sgx_my/sgx_my.json"));
        yesButton = new TextButton("Да", skin);
        noButton = new TextButton("Нет", skin);

        exitText = new Label("Вы действительно\nхотите выйти?", skin);
        exitWindow = new Window("", skin);
        exitWindow.setVisible(false);
        exitWindow.setModal(true);
        exitWindow.setMovable(false);
        float btn_height = height/16;
        exitWindow.setSize(exitText.getPrefWidth()+50, btn_height + exitText.getPrefHeight() + 45);
        System.out.println("label height = " + exitText.getPrefHeight());
        float btn_width = exitWindow.getWidth() / 2.5f;
        exitWindow.add(exitText).pad(15).width(100).expand();
        exitWindow.row();

        exitWindow.bottom();
        exitWindow.add(yesButton).padBottom(15).padLeft(10).expand().size(btn_width, btn_height);
        exitWindow.add(noButton).padBottom(15).padRight(10).expand().size(btn_width,btn_height);
        exitWindow.row();

        exitWindow.setPosition(width/2 - exitWindow.getWidth()/2, height/2- exitWindow.getHeight()/2);

        menuTexture = new Texture("MainMenuAtlas.png");

        titleSprite = new Sprite(menuTexture, 2, 289, 687, 613);
        settingsButtonSprite = new Sprite(menuTexture, 574, 2, 284, 285);
        exitButtonSprite = new Sprite(menuTexture, 691, 330, 284, 285);
        startButtonSprite = new Sprite(menuTexture, 288, 2, 284, 285);
        helpButtonSprite = new Sprite(menuTexture, 691, 617, 284, 285);
        achieveButtonSprite = new Sprite(menuTexture, 2, 2, 284, 285);

        float heightForTitle = height / 2.5f;
        float scale = heightForTitle / titleSprite.getHeight();


        scaleTitle = scale;
        titleSprite.setScale(scaleTitle);
        titleSpriteY = height - titleSprite.getHeight() * scaleTitle / 2;
        titleSpriteX = width / 2;
        titleSprite.setCenter(titleSpriteX, titleSpriteY);

        scaleButtonStart = height / 2 / 3 / startButtonSprite.getWidth();
        startButtonX = width / 2 - startButtonSprite.getWidth() * scaleButtonStart/ 2;
        startButtonY = height - titleSprite.getHeight() * scaleTitle - startButtonSprite.getWidth() * scaleButtonStart * 1.5f;// - height/2/3;

        scaleOtherButtons = height / 2 / 4 / settingsButtonSprite.getWidth();
        settingsButtonX = width / 6 - settingsButtonSprite.getWidth() * scaleOtherButtons / 2;
        settingsButtonY = startButtonY  - height / 2 / 3;//startButtonSprite.getWidth() * scaleOtherButtons - 10;

        achieveButtonX = width - settingsButtonX - settingsButtonSprite.getWidth() * scaleOtherButtons;
        achieveButtonY = settingsButtonY;



        helpButtonX = width / 4 - helpButtonSprite.getWidth() * scaleOtherButtons / 2;
        helpButtonY = settingsButtonY  - height / 2 /3;//- 10 - helpButtonSprite.getWidth() * scaleOtherButtons - 30;



        exitButtonX = width - helpButtonX - helpButtonSprite.getWidth() * scaleOtherButtons;
        exitButtonY = helpButtonY;


        startButtonDrawable = new SpriteDrawable(startButtonSprite);
        settingsButtonDrawable = new SpriteDrawable(settingsButtonSprite);
        helpButtonDrawable = new SpriteDrawable(helpButtonSprite);
        achieveButtonDrawable = new SpriteDrawable(achieveButtonSprite);
        exitButtonDrawable = new SpriteDrawable(exitButtonSprite);



        startImageButton = new ImageButton(startButtonDrawable);
        settingsImageButton = new ImageButton(settingsButtonDrawable);
        helpImageButton = new ImageButton(helpButtonDrawable);
        achieveImageButton = new ImageButton(achieveButtonDrawable);
        exitImageButton = new ImageButton(exitButtonDrawable);
        blackScreenImage = new Image(blackTexture);


        startButtonHeight = startButtonSprite.getHeight() * scaleButtonStart;
        startButtonWidth = startButtonSprite.getWidth() * scaleButtonStart;
        startImageButton.setSize(startButtonWidth, startButtonHeight);
        startImageButton.setPosition(startButtonX, startButtonY);

        otherButtonHeight = settingsButtonSprite.getHeight() * scaleOtherButtons;
        otherButtonWidth = settingsButtonSprite.getWidth() * scaleOtherButtons;

        settingsImageButton.setSize(otherButtonWidth, otherButtonHeight);
        settingsImageButton.setPosition(settingsButtonX, settingsButtonY);

        helpImageButton.setSize(otherButtonWidth, otherButtonHeight);
        helpImageButton.setPosition(helpButtonX, helpButtonY);

        achieveImageButton.setSize(otherButtonWidth, otherButtonHeight);
        achieveImageButton.setPosition(achieveButtonX, achieveButtonY);

        exitImageButton.setSize(otherButtonWidth, otherButtonHeight);
        exitImageButton.setPosition(exitButtonX, exitButtonY);

        blackScreenImage.setSize(width, height);
        blackScreenImage.setPosition(0, 0);

        viewport = new FitViewport(width, height);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);


        noButton.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                blackScreenImage.addAction(sequence(alpha(0, 0.5f), visible(false)));
                exitWindow.setVisible(false);

            }
        });

        yesButton.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();

            }
        });


        startImageButton.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new GameScreen());
                dispose();

            }

        });

        settingsImageButton.addListener(new ClickListener() {

            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen());
                dispose();


            }
        });

        helpImageButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HelpScreen());
                dispose();
            }
        });

        achieveImageButton.addListener(new ClickListener() {
            public void  clicked(InputEvent event, float x, float y) {
                System.out.println("achieveButton Pressed");
                game.setScreen(new AchievementsScreen());
                dispose();
            }
        });

        exitImageButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("exit button pressed");
                exitWindow.setVisible(true);
                blackScreenImage.addAction(visible(true));
                blackScreenImage.setColor(1,1,1, 0);
                blackScreenImage.addAction(alpha(0.7f, 0.5f));
                stage.addActor(blackScreenImage);
                stage.addActor(exitWindow);
            }
        });


        stage.addActor(startImageButton);
        stage.addActor(settingsImageButton);
        stage.addActor(helpImageButton);
        stage.addActor(achieveImageButton);
        stage.addActor(exitImageButton);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        batch.begin();
        backgroundSprite.draw(batch);
        titleSprite.draw(batch);

        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        menuTexture.dispose();
        stage.dispose();
        blackTexture.dispose();
        skin.dispose();
    }
}
