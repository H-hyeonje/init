document.addEventListener('DOMContentLoaded', function() {
  var quill = new Quill('#editor', {
    theme: 'snow', // 'snow' 또는 'bubble' 테마를 선택
    modules: {
      toolbar: [
        [{ 'header': '1' }, { 'header': '2' }, { 'font': [] }],
        [{ 'list': 'ordered'}, { 'list': 'bullet' }],
        ['bold', 'italic', 'underline'],
        ['link'],
        [{ 'align': [] }],
        ['image', 'video'],
        ['color'] // 색상 선택 기능 추가
      ]
    }
  });
});