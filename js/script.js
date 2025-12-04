// Auto-calculate total fee
const subjects = document.querySelectorAll(".subject");
const totalBox = document.getElementById("total");
const outputBox = document.getElementById("outputBox");
const form = document.getElementById("regForm");
const nameInput = document.getElementById("name");

// Helper: update the displayed total
function updateTotal() {
    let total = 0;
    subjects.forEach(sub => {
        if (sub.checked) total += Number(sub.value) || 0;
    });
    totalBox.innerText = "₹" + total;
}

// initial total (in case some checkboxes are pre-checked)
updateTotal();

// listen for changes on each checkbox
subjects.forEach(item => {
    item.addEventListener("change", updateTotal);
});

// handle form submission (show result below form)
form.addEventListener("submit", function(e) {
    e.preventDefault();

    // collect selected subjects
    let selectedSubjects = [];
    let totalFee = 0;

    subjects.forEach(sub => {
        if (sub.checked) {
            // since input is inside label, parentElement.textContent gives the label text
            let subjectName = sub.parentElement.textContent.trim();
            selectedSubjects.push(subjectName);
            totalFee += Number(sub.value) || 0;
        }
    });

    if (selectedSubjects.length === 0) {
        alert("Please select at least one subject.");
        return;
    }

    let studentName = nameInput.value.trim();

    if (studentName === "") {
        alert("Please enter student name.");
        nameInput.focus();
        return;
    }

    // Build the message with line breaks
    let messageLines = [];
    messageLines.push("Student Name: " + studentName);
    messageLines.push("");
    messageLines.push("Selected Subjects:");
    selectedSubjects.forEach(s => messageLines.push("- " + s));
    messageLines.push("");
    messageLines.push("Total Fee: ₹" + totalFee);

    // Display below form
    outputBox.style.display = "block";
    outputBox.innerText = messageLines.join("\n");

    // scroll to output for smaller screens
    outputBox.scrollIntoView({ behavior: "smooth", block: "center" });
});
