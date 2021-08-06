<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div id = "nav">
    <router-link v-bind:to="{name:'addRecipe'}">Add Recipe</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'addIngredient'}">Add Ingredient</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'recipeList'}">View my recipes</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'createMealPlan'}">Add Meal Plan</router-link> &nbsp;|&nbsp;
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
#nav a{
  font-weight: bold;
}

</style>