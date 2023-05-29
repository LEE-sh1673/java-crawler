const filterContainer = document.querySelector(".filter-control");
const filterForm = document.querySelector("#filter-form");
const searchForm = filterForm.querySelector("input[type=text]");
const checkBoxes = filterForm.querySelectorAll("input[type=checkbox]");
const radioButtons = filterForm.querySelectorAll("input[type=radio]");
const filterClose = document.querySelector("#filter-close");

const skillForm = document.querySelector("#skill-form");
const userSkillSelectBox = skillForm?.querySelector("input[type=checkbox]");

if (skillForm != null) {
  skillForm.addEventListener("submit", (e) => {
    e.preventDefault();
  })
}

filterForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const name = searchForm.value;
  const dateOptions = nonEmptyList("dateOption");
  const applicantTypes = nonEmptyList("applicantTypes");
  const awardScales = nonEmptyList("awardScale");
  const awardBenefits = nonEmptyList("awardBenefits");
  const skills = nonEmptyList("skills");
  const moimType = nonEmptyList("moimType");

  const searchParams = new URLSearchParams();

  if (name !== '') {
    searchParams.append("name", name);
  }
  for (const dateOption of dateOptions) {
    searchParams.append("dateOption", dateOption);
  }
  for (const applicantType of applicantTypes) {
    searchParams.append("applicantTypes", applicantType);
  }
  for (const awardScale of awardScales) {
    searchParams.append("awardScale", awardScale);
  }
  for (const awardBenefit of awardBenefits) {
    searchParams.append("awardBenefits", awardBenefit);
  }

  if (skillForm != null && userSkillSelectBox.checked) {
    const userSkills = [...document.querySelectorAll(
        "input[name=memberSkill]")].map(el => el.value);

    for (const skill of userSkills) {
      searchParams.append("skills", skill);
    }
  } else {
    for (const skill of skills) {
      searchParams.append("skills", skill);
    }
  }
  for (const el of moimType) {
    searchParams.append("moimType", el);
  }
  const url = window.location.href;
  window.location.href = url.split('?')[0] + '?' + searchParams.toString();
});

function nonEmptyList(selectorName) {
  const elems = [...document.querySelectorAll(`input[name=${selectorName}]`)]
  return elems.filter(el => el.checked).map(el => el.value);
}

function clearForm() {
  searchForm.value = '';
  checkBoxes.forEach(checkbox => checkbox.checked = false);
  radioButtons.forEach(radioButton => radioButton.checked = false);
}

filterClose.addEventListener("click", () => {
  filterContainer.classList.toggle("scroll");
});