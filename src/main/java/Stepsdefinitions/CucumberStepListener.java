package Stepsdefinitions;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class CucumberStepListener implements ConcurrentEventListener {

	private static final ThreadLocal<String> CURRENT_STEP = new ThreadLocal<>();

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
	}

	private void onTestStepStarted(TestStepStarted event) {
		if (event.getTestStep() instanceof PickleStepTestStep pickleStep) {
			String keyword = pickleStep.getStep().getKeyword() == null ? "" : pickleStep.getStep().getKeyword().trim();
			String text = pickleStep.getStep().getText();
			String label = (keyword + " " + text).trim();
			CURRENT_STEP.set(label);
		}
	}

	public static String getCurrentStep() {
		return CURRENT_STEP.get();
	}

	public static void clear() {
		CURRENT_STEP.remove();
	}
}
