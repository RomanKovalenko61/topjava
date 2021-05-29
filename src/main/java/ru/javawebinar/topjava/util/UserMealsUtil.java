package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2021, Month.MAY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(9, 0), LocalTime.of(21, 0), 2000);
        mealsTo.forEach(System.out::println);

    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> filteredList = new ArrayList<>();
        Map<LocalDate, Integer> mapCaloriesPerDay = new HashMap<>();

        for (UserMeal userMeal : meals) {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            int calories = userMeal.getCalories();
            mapCaloriesPerDay.put(localDate, mapCaloriesPerDay.getOrDefault(localDate, 0) + calories);
        }

        for (UserMeal userMeal : meals) {
            LocalDateTime localDateTime = userMeal.getDateTime();
            LocalDate localDate = localDateTime.toLocalDate();
            LocalTime localTime = localDateTime.toLocalTime();
            if (isBetweenHalfOpen(localTime, startTime, endTime)) {
                filteredList.add(new UserMealWithExcess(localDateTime, userMeal.getDescription(), userMeal.getCalories(), mapCaloriesPerDay.get(localDate) > caloriesPerDay));
            }
        }

        return filteredList;
    }

}
