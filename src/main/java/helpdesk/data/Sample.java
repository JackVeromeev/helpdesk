package helpdesk.data;

import helpdesk.data.abstr.HasQuestion;

public class Sample implements HasQuestion{
	private final String question;
	private final String answer;

	public Sample(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "Sample{" +
				"question='" + question + '\'' +
				", answer='" + answer + '\'' +
				'}';
	}
}
