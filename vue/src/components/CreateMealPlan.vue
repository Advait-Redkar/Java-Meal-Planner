<template>
  <div id="add">
    <div>
      <div v-if="validationFailMsg.length > 0"> <!--DIY Validation Step 4 Add a div that shows the error if there is one. You should style this yourself-->
        Error: {{validationFailMsg}}
      </div>
      <form>
        Meal Plan Name:
        <input
          type="text"
          class="form-control"
          v-model="newMealPlan.mealplanName"
          required="true"
        />
        Day of Week:
        <select 
        v-model="newMealPlan.mealplanDay" 
        
         :close-on-select="false"
          :clear-on-select="false"
          :preserve-search="true"
          placeholder="Day of Week"
          track-by="mealplanDay"
          required="true"
          >
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
          :allow-empty="false"
          required
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
        mealplanDay: null,
        mealList: [],
      },
      mealList: [],
      mealplanDays: [1, 2, 3, 4, 5, 6, 7],
      validationFailMsg:"",
    };
  },
  methods: {
    checkValidation() {
      this.validationFailMsg = "";
      if (this.newMealPlan.mealplanName.length === 0) {
        this.validationFailMsg += "Meal plan name is required\n";
      }
      if (this.newMealPlan.mealplanDay === null) {
        this.validationFailMsg += "Meal plan day is required\n";
      }
      if (this.newMealPlan.mealList.length === 0) {
        this.validationFailMsg += "At least one meal is required";
      }
      return this.validationFailMsg.length ===0;
    },
    createMealPlan() {
      if(this.checkValidation()){
      mealPlanService
        .addMealPlan(this.newMealPlan)
        .then((response) => {
          if (response.status === 200) {
            alert("Meal Plan Succesfully Added");
            this.$router.push({ name: "home" });
            this.newMealPlan={};
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
      }
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
