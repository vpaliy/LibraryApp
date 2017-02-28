package com.vpaliy.data.specification;

public interface SQLSpecification extends Specification {
    String[] getProjection();
    String getSelection();
    String[] getSelectionArgs();
    String getOrder();
}
