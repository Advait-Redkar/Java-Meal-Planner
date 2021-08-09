<template>
  <div id="add">
    <div>
      <form>
        Meal Plan Name:
        <input
          type="text"
          class="form-control"
          v-model="newMealPlan.mealplanName"
        />
        Day of Week:
        <select 
        v-model="newMealPlan.mealplanDay" 
        
         :close-on-select="false"
          :clear-on-select="false"
          :preserve-search="true"
          placeholder="Day of Week"
          track-by="mealplanDay">
          <option value=1>Monday</option>
          <option value=2>Tuesday</option>
          <option value=3>Wednesday</option>
          <option value=4>Thursday</option>
          <option value=5>Friday</option>
          <option value=6>Saturday</option>
          <option value=7>Sunday</option>
        </select>

        <multiselect
          v-model="newMealPlan.mealList"
          :options="mealList"
          label="mealName"
          :multiple="true"
          :close-on-select="false"
          :clear-on-select="false"
          :preserve-search="true"
          placeholder="Select Your Meal"
          track-by="mealId"
          @remove="toggleUnSelectMeal"
        ></multiselect>
        <div class="submit-btn">
        <button type="submit" class="btn" v-on:click="createMealPlan">
          Add Meal Plan
        </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import MealService from "@/services/MealService";
import mealPlanService from "@/services/mealPlanService";
import Multiselect from "vue-multiselect";
export default {
  name: "create-meal-plan",
  components: { Multiselect },
  data() {
    return {
      newMealPlan: {
        mealplanName: "",
        mealplanTime: "",
        mealplanDay: null,
        mealList: [],
      },
      mealList: [],
      mealplanDays: [1, 2, 3, 4, 5, 6, 7]
    };
  },
  methods: {
    createMealPlan() {
      mealPlanService
        .addMealPlan(this.newMealPlan)
        .then((response) => {
          if (response.status === 200) {
            alert("Meal Plan Succesfully Added");
            this.$router.push({ name: "home" });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding meal plan. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg =
              "Error adding meal plan. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error adding meal plan. Request could not be created.";
          }
        });
    },
    toggleUnSelectMeal(mealId) {
      this.newMealPlan.meals = this.newMealPlan.mealList.filter((meal) => {
        return meal.mealId != mealId;
      });
    },
  },
  computed: {
    options: () => this.mealList,
    options2:() => this.mealplanDays
  },
  created() {
    MealService.viewMeals().then((response) => {
      this.mealList = response.data;
      //this.isLoading = false;
    });
  },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style></style>
