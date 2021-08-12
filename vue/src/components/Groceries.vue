<template>
  <div class="main-div">
    <div class="div-subclass">
      <div class="recipe-name">
        <h1>Grocery List</h1>
      </div>
      <div
        v-for="ingredient in ingredients"
        v-bind:key="ingredient.ingredientId"
      >
        <div>
          <h2>{{ ingredient.ingredientName }}</h2>
        </div>
      </div>
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