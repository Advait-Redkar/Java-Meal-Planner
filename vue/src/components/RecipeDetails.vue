<template>
  <div class="main-div">
    <div class="div-subclass">
    <div class="recipe-name">
      <h1>{{ recipe.recipeName }}</h1>
    </div>
    <div class="details">
    <div>
      <h2 class="test">Description</h2>
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
  margin-bottom: 20px;
  padding-bottom: 20px;
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
 /*  margin: 0 auto 0 auto;*/
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 0px;

}
/*.div-subclass{
  display: flex;
  flex-direction: column;
  align-items: center;
  align-items: flex-start;
  padding-bottom: 0px;

}*/
.recipe-name{
  /* display: flex; */
  color: #994000;
  /* align-self: center; */
  text-align: center;
}

.details{
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  padding-left: 10%;
  padding-right:10%;


}

@media only screen and (max-width: 1000px){
.main-div{
  display: flex;
  flex-direction: column;
  border: none;
  background-color: #ffbf80;
  justify-content: center; 
  width: 25%;
  margin: 0 auto 0 auto;
  font-family: 'Barlow Condensed', sans-serif;
  font-weight: bold;
  margin-bottom: 20px;
  padding-bottom: 20px;
}
}

/*Added:
1. text-align: center to .recipe-name (in)
2. added .details to center middle info in recipe details
3. took off list-btn-div class of grocery items and displayed them as h2s in a div
rather than lis in a ul
4. added text-align: center; to submit-btn to center the add buttons
5.edited how mealplan list dispalys so are h2s and p instead of ul and li
6.media-query
7.added margin on logout button



*/
</style>

