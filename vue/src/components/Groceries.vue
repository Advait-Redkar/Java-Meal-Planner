<template>
  <div class="main-div">
    <div class="div-subclass">
      <div class="recipe-name">
        <h1>Grocery List</h1>
      </div>
      <ul
        v-for="ingredient in ingredients"
        v-bind:key="ingredient.ingredientId"
      >
        <li class="list-btn-div">
          {{ ingredient.ingredientName }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import mealPlanService from "@/services/mealPlanService";

export default {
  name: "groceries",
  data() {
    return {
      ingredients: [],
    };
  },
  created() {
    mealPlanService
      .viewGroceries(this.$route.params.mealplanId)
      .then((response) => {
        this.ingredients = response.data;
      });
  },
};
</script>

<style>
</style>