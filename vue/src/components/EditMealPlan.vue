<template>
<div class="main-div">
   <form v-on:submit.prevent class="form-edit">
       <h2>Meal Plan Name:</h2>
        <input type="text" class="form-control" v-model="newMealplan.mealplanName" />
       <h2>Day of Week:</h2>
        <select 
        v-model="newMealplan.mealplanDay" 
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
          v-model="newMealplan.mealList"
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

        <div class="button-div">
        <button type="submit" class="btn" v-on:click="updateMealplan()">Update Meal Plan</button>
        </div>
      </form>
      </div>
</template>

<script>
import Multiselect from 'vue-multiselect';
import mealPlanService from '@/services/mealPlanService';
import MealService from '@/services/MealService';

export default {
 components:{Multiselect,},
  name: "edit-meal-plan",
  props: [
      "mealplanId"
  ],
  data() {
    return {
      newMealplan: {
        mealplanName: "",
        mealplanDay: null,
        mealList:[]
      },
       mealList:[]
    };
  },
  methods: {
      
    toggleUnSelectMeal(mealId){
      this.newMealplan.mealList = this.newMealplan.mealList.filter((meal)=>{
          return meal.mealId!=mealId;
      })

    },
   
    updateMealplan() {
        const mealplan ={
        mealplanId: this.$route.params.mealplanId,
        mealplanName: this.newMealplan.mealplanName,
        mealplanDay: this.newMealplan.mealplanDay,
        mealList: this.newMealplan.mealList
        };
      
       mealPlanService.editMealPlan(this.$route.params.mealplanId, mealplan)
        .then (response=>{
            if(response.status===200){
            alert("Successfully updated");
            this.$router.push({name: 'mealPlanDetails'});
            }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error updating meal plan. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg = "Error updating meal plan. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error updating meal plan. Request could not be created.";
          }
        });
    }

  },
  computed: {
    options: () => this.mealList,
  },
  created() {
    mealPlanService.viewMealPlanDetails(this.mealplanId)
    .then((response) => {
      this.newMealplan.mealplanName = response.data.mealplanName;
      this.newMealplan.mealplanDay = response.data.mealplanDay;
    }),    
    MealService.viewMeals().then((response) => {
      this.mealList = response.data;
      //this.isLoading = false;
    });
  
  }
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}



#add {
  display:flex;
  justify-content: center;

}

.form-edit{
  border: none;
  padding: 10px;
  border-radius: 10px;

}
.main-div{
  width: 30%;
}
.button-div{
  margin: 10px auto 0 auto;
  
}
</style>