<template>
  <div>
      <h1>{{recipe.recipeName}}</h1>
      <p>{{recipe.description}}</p>
      <p>{{recipe.instructions}}</p>
  </div>
</template>

<script>
//import recipeService from "@/services/RecipeService";

export default {
    name:"recipe-list",
    props: {
        recipe: Object
    }
    }
    

</script>

<style>

</style>