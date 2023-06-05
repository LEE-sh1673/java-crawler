const avatar = document.querySelector('#user__avatar');
const loginBtn = document.querySelector("#user__login-btn");
const skillSearch = document.querySelector("#user__skills__search");
const bookmarkBtn = document.querySelector("#bookmark");
const bookmarkCloseBtns = document.querySelectorAll("button.bookmark__close");
const userWithdrawal = document.querySelector("#withdrawal");

if (avatar != null) {
  const profile = document.querySelector("#user__option");

  avatar.addEventListener("click", () => {
    profile.classList.toggle("d-none");
  })
}

if (loginBtn != null) {
  const loginSelect = document.querySelector("#user__login-select");

  loginBtn.addEventListener("click", () => {
    loginSelect.classList.toggle("d-none");
  });
}

if (skillSearch != null) {
  const skillForm = document.querySelector("#user__skills__form");
  const closeBtn = document.querySelector("#user__skills__close-btn");

  skillSearch.addEventListener("click", () => {
    skillForm.classList.toggle("d-none");
  });

  closeBtn.addEventListener("click", () => {
    skillForm.classList.toggle("d-none");
  });
}

if (userWithdrawal != null) {
  const withdrawalForm = document.querySelector("#withdrawal__box");
  const closeBtn = document.querySelector("#withdrawal__box__close-btn");

  userWithdrawal.addEventListener("click", () => {
    withdrawalForm.classList.toggle("d-none");
  })
  closeBtn.addEventListener("click", () => {
    withdrawalForm.classList.toggle("d-none");
  });
}

$(document).ready(function () {

  if (skillSearch != null) {
    $('#user__skills__form').submit((e) => {
      e.preventDefault();

      const skillData = [];
      $('input[name="skills"]:checked').each(function () {
        skillData.push($(this).val());
      });

      const data = {
        skills: skillData
      };

      $.ajax({
        type: 'PUT',
        url: '/api/v1/skills',
        dataType: 'Text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

      }).done(() => {
        alert('수정되었습니다.');
        window.location.href = '/profile';

      }).fail((error) => {
        alert(JSON.stringify(error));
      });
    });
  }

  if (userWithdrawal != null) {
    $('#btn__withdrawal').on('click', () => {
      const id = $('#member_id').val();

      $.ajax({
        type: 'DELETE',
        url: '/api/v1/members/' + id,
        dataType: 'Text',
        contentType: 'application/json; charset=utf-8'
      }).done(function () {
        alert('탈퇴되었습니다. 지금까지 서비스를 이용해주셔서 감사합니다.')
        window.location.href = '/';
      }).fail(function (error) {
        alert(JSON.stringify(error));
      });
    });
  }

  if (bookmarkBtn != null) {
    $('#bookmark').on('click', () => {
      const data = {
        memberId: $('#member_id').val(),
        eventId: $('#event_id').val()
      };

      console.log(data);

      $.ajax({
        type: 'POST',
        url: '/api/v1/bookmarks',
        dataType: 'Text',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
      }).done(function () {
        window.location.reload();
      }).fail(function (error) {
        alert(JSON.stringify(error));
      });
    });
  }

  bookmarkCloseBtns?.forEach(closeBtn => {
    closeBtn.addEventListener('click', () => {
      const id = closeBtn.querySelector("input[type='hidden']")?.value;

      if (id !== null) {
        $.ajax({
          type: 'DELETE',
          url: '/api/v1/bookmarks/' + id,
          dataType: 'Text',
          contentType: 'application/json; charset=utf-8'
        }).done(function () {
          alert('글이 삭제되었습니다.');
          window.location.reload();
        }).fail(function (error) {
          alert(JSON.stringify(error));
        });
      }
    });
  });
});
