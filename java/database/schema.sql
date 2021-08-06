BEGIN TRANSACTION;



/*DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS mealplan;
DROP TABLE IF EXISTS recipes_ingredients;
DROP TABLE IF EXISTS users_recipes;
DROP TABLE IF EXISTS mealplan_recipes;
DROP TABLE IF EXISTS users;
*/
DROP TABLE IF EXISTS users, recipes, ingredients, mealplan, recipes_ingredients, users_recipes, mealplan_recipes CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE recipes (
	recipe_id serial,
	recipe_name varchar(50) NOT NULL,
	recipe_instructions varchar(1000) NOT NULL,
	recipe_description varchar(1000) NOT NULL,
	CONSTRAINT PK_recipe PRIMARY KEY (recipe_id)
);

CREATE TABLE ingredients (
	user_id int NOT NULL,
	ingredient_id serial,
	ingredient_name varchar(50) NOT NULL,
	CONSTRAINT PK_ingredient PRIMARY KEY (ingredient_id),
	CONSTRAINT FK_user FOREIGN KEY(user_id) REFERENCES users(user_id)
);


CREATE TABLE mealplan (
	user_id int,
	mealplan_id serial,
	mealplan_name varchar(50) NOT NULL,
	mealplan_time varchar(50) NOT NULL,
	mealplan_day int,
	CONSTRAINT PK_mealplan PRIMARY KEY(mealplan_id),
	CONSTRAINT FK_usermp FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT CHK_mealplan_day CHECK (mealplan_day<=7)
);
CREATE TABLE mealplan_recipes(
mealplan_id int,
recipe_id int,
CONSTRAINT FK_mealplan FOREIGN KEY(mealplan_id) REFERENCES mealplan(mealplan_id),
CONSTRAINT FK_recipemr FOREIGN KEY(recipe_id) REFERENCES recipes(recipe_id)
);

CREATE TABLE recipes_ingredients (
	ingredient_id int,
	recipe_id int,
	/*CONSTRAINT PK_ingredientp PRIMARY KEY (ingredient_id),*/
	CONSTRAINT FK_ingredientf FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id),
	CONSTRAINT FK_recipe FOREIGN KEY(recipe_id) REFERENCES recipes(recipe_id)
);

CREATE TABLE users_recipes (
	user_id int,
	recipe_id int,
	/*CONSTRAINT PK_userp PRIMARY KEY (user_id),*/
	CONSTRAINT FK_userf FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_recipef FOREIGN KEY(recipe_id) REFERENCES recipes(recipe_id)
);


COMMIT TRANSACTION;