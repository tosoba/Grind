from typing import Set, List, Dict

from util.files import stripped_input_lines_from


class Food:
    def __init__(self, line: str) -> None:
        if '(' in line:
            self._ingredients = {ingredient.strip() for ingredient in line[:line.index('(')].strip().split(' ')}
            self._allergens = {allergen.replace(')', '').replace('contains', '').strip()
                               for allergen in line[line.index('(') + 1:].strip().split(',')}
        else:
            self._ingredients = {i.strip() for i in line.strip().split(' ')}
            self._allergens = set()

    def __str__(self) -> str:
        return self._ingredients.__str__() + '\n' + self._allergens.__str__()

    @property
    def allergens(self) -> Set[str]:
        return self._allergens

    @property
    def ingredients(self) -> Set[str]:
        return self._ingredients


def count_ingredients_with_no_allergens_from(path: str) -> int:
    foods: List[Food] = [Food(line) for line in stripped_input_lines_from(path)]
    all_allergens: Set[str] = set()
    all_ingredients: Set[str] = set()
    for food in foods:
        all_allergens.update(food.allergens)
        all_ingredients.update(food.ingredients)
    ingredients_with_allergens_map: Dict[str, Set[str]] = {allergen: set() for allergen in all_allergens}
    for food in foods:
        for allergen in food.allergens:
            if ingredients_with_allergens_map[allergen] == set():
                ingredients_with_allergens_map[allergen].update(food.ingredients)
            else:
                ingredients_with_allergens_map[allergen].intersection_update(food.ingredients)
    ingredients_with_allergens: Set[str] = set()
    for ingredients in ingredients_with_allergens_map.values():
        ingredients_with_allergens.update(ingredients)
    ingredients_with_no_allergens = {ingredient for ingredient in all_ingredients
                                     if ingredient not in ingredients_with_allergens}
    result = 0
    for food in foods:
        for ingredient in food.ingredients:
            if ingredient in ingredients_with_no_allergens:
                result += 1
    return result


def ingredients_with_allergens_list_from(path: str) -> str:
    foods: List[Food] = [Food(line) for line in stripped_input_lines_from(path)]
    all_allergens: Set[str] = set()
    all_ingredients: Set[str] = set()
    for food in foods:
        all_allergens.update(food.allergens)
        all_ingredients.update(food.ingredients)

    ingredients_with_allergens_map: Dict[str, Set[str]] = {allergen: set() for allergen in all_allergens}
    for food in foods:
        for allergen in food.allergens:
            if ingredients_with_allergens_map[allergen] == set():
                ingredients_with_allergens_map[allergen].update(food.ingredients)
            else:
                ingredients_with_allergens_map[allergen].intersection_update(food.ingredients)

    determined_ingredients: Set[str] = set()
    while any(len(ingredients) != 1 for ingredients in ingredients_with_allergens_map.values()):
        for allergen, ingredients in ingredients_with_allergens_map.items():
            if len(ingredients) == 1:
                ingredient_to_remove = next(iter(ingredients))
                if ingredient_to_remove in determined_ingredients:
                    continue
                for other_allergen, other_ingredients in ingredients_with_allergens_map.items():
                    if other_allergen != allergen and ingredient_to_remove in other_ingredients:
                        other_ingredients.remove(ingredient_to_remove)
                determined_ingredients.add(ingredient_to_remove)
                break

    canonical_ingredients = []
    for _, ingredients in sorted(ingredients_with_allergens_map.items(), key=lambda entry: entry[0]):
        assert len(ingredients) == 1
        canonical_ingredients.append(next(iter(ingredients)))

    return ','.join(canonical_ingredients)


if __name__ == '__main__':
    print(ingredients_with_allergens_list_from('d21_allergens_input.txt'))
