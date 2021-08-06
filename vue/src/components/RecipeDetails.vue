<template>
  <div>
    <div>
      <h1>Recipe Name</h1>
      <h2>{{ recipe.recipeName }}</h2>
    </div>
    <div>
      <h3>Recipe Description</h3>
      <p>{{ recipe.description }}</p>
    </div>
    <div>
      <h3>Recipe Instructions</h3>
      <p>{{ recipe.instructions }}</p>
    </div>
    <div>
      <h3>Recipe Ingredients</h3>
      <ul
        id="example-1"
        v-for="ingredients in recipe.ingredients"
        v-bind:key="ingredients.ingredientName"
      >
        <li>
          {{ ingredients.ingredientName }}
        </li>
      </ul>
    </div>
    <router-link
      v-bind:to="{ name: 'editRecipe', params: { recipeId: recipe.recipeId } }"
    >
      <button>
        Edit Recipe
      </button>
    </router-link>
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

<style></style>
