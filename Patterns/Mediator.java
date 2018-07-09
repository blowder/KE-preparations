class Mediator {
    static interface Component {

    }

    static interface MediatorI {
        void notifyMediator(Component component, String event);
    }

    static class Button implements Component {
        private MediatorI mediator;

        public Button(MediatorI mediator) {
            this.mediator = mediator;
        }

        public void press() {
            System.out.println("Button was pressed");
            this.mediator.notifyMediator(this, "pressed");
        }
    }

    static class TextBox implements Component {
        private MediatorI mediator;

        public TextBox(MediatorI mediator) {
            this.mediator = mediator;
        }

        public void showText(String text) {
            System.out.println("Text in textbox is " + text);
            this.mediator.notifyMediator(this, "textShowed");
        }

        public void hover() {
            System.out.println("Mouse pointer is over text");
            this.mediator.notifyMediator(this, "hover");
        }
    }

    static class Tooltip implements Component {
        private MediatorI mediator;

        public Tooltip(MediatorI mediator) {
            this.mediator = mediator;
        }

        public void showTooltip() {
            System.out.println("Tooltip was shown");
        }
    }

    static class Dialog implements MediatorI {
        private Button button;
        private TextBox textBox;
        private Tooltip tooltip;

        public void setButton(Button button) {
            this.button = button;
        }

        public void setTextBox(TextBox textBox) {
            this.textBox = textBox;
        }

        public void setTooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
        }

        public void notifyMediator(Component component, String event) {
            if (component == button && event.equals("pressed")) {
                textBox.showText("Hello");
            }
            if (component == textBox && event.equals("hover")) {
                tooltip.showTooltip();
            }
        }

    }

    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        Button button = new Button(dialog);
        TextBox textBox = new TextBox(dialog);
        Tooltip tooltip = new Tooltip(dialog);
        dialog.setButton(button);
        dialog.setTextBox(textBox);
        dialog.setTooltip(tooltip);

        button.press();
        textBox.hover();
    }
}