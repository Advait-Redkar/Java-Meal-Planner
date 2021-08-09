<template>
  <div class="main-div">
    <div class="div-subclass">
    <div class="recipe-name">
      <h1>{{ recipe.recipeName }}</h1>
    </div>
    <div>
      <h2>Description</h2>
      <h3>{{ recipe.description }}</h3>
    </div>
    <div>
      <h2>Instructions</h2>
      <h3>{{ recipe.instructions }}</h3>
    </div>
    <div>
      <h2>Ingredients</h2>
      <ul
        id="example-1"
        v-for="ingredients in recipe.ingredients"
        v-bind:key="ingredients.ingredientName"
      >
        <h3><li>
          {{ ingredients.ingredientName }}
        </li>
        </h3>
      </ul>
    </div>
    <router-link
      v-bind:to="{ name: 'editRecipe', params: { recipeId: recipe.recipeId } }"
    >
      <button class="edit-button">
        Edit Recipe
      </button>
    </router-link>
    </div>
  </div>
 
</template>

<script>
export default {
  name: "recipe-details",
  props: {
    recipe: Object,
  },
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@100&display=swap');
</style>
<style>
.main-div{
  display: flex;
  flex-direction: column;
  border: 4px solid #FA6900;
  background-color: #FFF2E6;
  justify-content: center; 
  width: 25%;
  margin: 0 auto 0 auto;
  font-family: 'Barlow Condensed', sans-serif;
  font-weight: bold;
}
.edit-button{
    display:inline-block;
padding:0.5em 3em;
border:0.16em solid #F38630;
margin:0 0.3em 0.3em 0;
box-sizing: border-box;
text-decoration:none;
text-transform:uppercase;
font-family:'Roboto',sans-serif;
font-weight:400;
background-color:white;
text-align:center;
transition: all 0.15s;
color:black;
}
.edit-button:hover{
   color:black;
border-color:black;
background-color: whitesmoke;
}
.div-subclass{
  margin: 0 auto 0 auto;
}
.recipe-name{
  text-decoration: underline;
  justify-self: flex-start;
}
</style>
