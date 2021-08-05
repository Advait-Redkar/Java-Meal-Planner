<template>
  <div>
      <h1>Recipe List</h1>
      <div v-for="recipe in recipes" v-bind:key="recipe.recipeId">
          <div>
              <h2>{{recipe.recipeName}}</h2>
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
            recipes:[]
        }
    },
    created(){
            recipeService
                .listAllRecipes()
                .then((response)=>{
                    this.recipes = response.data;
                });
        }
    }

</script>

<style>

</style>