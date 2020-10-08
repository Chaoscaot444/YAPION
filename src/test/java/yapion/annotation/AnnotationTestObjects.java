package yapion.annotation;

import yapion.annotations.object.YAPIONData;
import yapion.annotations.object.YAPIONPostDeserialization;
import yapion.annotations.object.YAPIONPreDeserialization;
import yapion.annotations.serialize.YAPIONOptimize;

public class AnnotationTestObjects {

    @YAPIONData
    public static class PreTest {

        @YAPIONPreDeserialization(context = {"exception"})
        private void pre() {
            throw new IllegalArgumentException();
        }

        @YAPIONPreDeserialization(context = {"noException"})
        private void preOther() {

        }

    }

    @YAPIONData
    public static class PostTest {

        @YAPIONPostDeserialization(context = {"exception"})
        private void post() {
            throw new IllegalArgumentException();
        }

        @YAPIONPostDeserialization(context = "noException")
        private void postOther() {

        }

    }

    @YAPIONData
    public static class OptimizeTest {

        @YAPIONOptimize
        private String s;

        public OptimizeTest(String s) {
            this.s = s;
        }

    }

}
