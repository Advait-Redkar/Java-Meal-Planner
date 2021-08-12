<template>
  <div class="main-div">
        <!-- <ul>
            <li v-for="recipe in searchForRecipeByIngredient" v-bind:key="recipe">Recipe Name : {{recipe.recipeName}}</li>
        </ul> -->
      <div class="div-subclass">
       <div class="recipe-name">   
      <h1>Recipe List</h1>
      <input class="search-bar" type="text" v-model="ingredientSearch" placeholder="Search by Ingredient"/>
       </div>
      <div v-for="recipe in searchForRecipeByIngredient" v-bind:key="recipe.recipeId">
          <div class="list-btn-div">
              <router-link v-bind:to="{name: 'recipeDetails', params: {recipeId:recipe.recipeId}}">
              <button class="list-button">{{recipe.recipeName}}</button>
              </router-link>
              </div> 
      </div>
      </div>

  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";

export default {
    name:"recipe-list",
    data(){
        return{
            recipes:[],
            ingredientSearch:"",
        }
    },
    created(){
            recipeService
                .listAllRecipes()
                .then((response)=>{
                    this.recipes = response.data;
                });
        },
        computed:{
            /*this.recipes has an array of ingredients 
              We want to filter the recipes and only keep ones that have ingredients search
              As an ingredient.ingredientName*/ 
            searchForRecipeByIngredient(){
                return this.recipes.filter(recipe=>{
                     if(!recipe.ingredients){return false}
                     if(this.ingredientSearch.length==0){return true}
                    return recipe.ingredients.findIndex(ingredient =>{
                        return ingredient.ingredientName.toLowerCase()
                         === this.ingredientSearch.toLowerCase()
                     }) >=0
                })
            }
        }
    }

</script>

<style>
.list-button{
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
width: 250px;
}

.list-btn-div{
    display: flex;
   /* flex-wrap: wrap;
    width: 200px;*/
}

.search-bar{
    border:0.16em solid #F38630;
    width: 242px;
    margin: 0 0 10px 0;
    height: 28px;
}






</style>