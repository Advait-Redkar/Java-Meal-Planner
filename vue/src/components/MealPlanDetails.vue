<template>
  <div class="main-div">
    <div class="div-subclass">
      <div class="recipe-name">
        <h1>{{ dayOfWeekMapping[mealplan.mealplanDay] }}: {{ mealplan.mealplanName }}</h1>
      </div>
      <!-- <div>
        <h2>{{ dayOfWeekMapping[mealplan.mealplanDay] }}</h2>
      </div> -->
      <div  id="mealplan">
        <div
          v-for="meals in mealplan.mealList"
          v-bind:key="meals.mealName"
        >
          <h2 class="mealType-h2">
            {{ mealTypeMapping[meals.mealType] }}
          </h2>

          <p>
            {{ meals.mealName }}
          </p>
        </div>
      </div>
      <router-link
      v-bind:to="{ name: 'editMealplan', params: { mealplanId: mealplan.mealplanId } }"
    >
      <button class="edit-button">
        Edit Meal Plan
      </button>
    </router-link>
     <router-link
      v-bind:to="{ name: 'groceries', params: { mealplanId: mealplan.mealplanId } }"
    >
      <button class="edit-button">
        View Grocery List
      </button>
    </router-link>
    
    </div>
  </div>
</template>

<script>
export default {
  props: {
    mealplan: Object,
  },
  data() {
    return {
      dayOfWeekMapping: [
        "unused",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
      ],
      mealTypeMapping: ["unused", "Breakfast", "Lunch", "Dinner", "Snack"],
    };
  },
};
</script>
<style>
#mealplan {
  padding-bottom: 10px;
}

</style>
