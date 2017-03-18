package com.vpaliy.mvp.di.component;

import com.vpaliy.mvp.di.scope.ViewScope;
import dagger.Component;

@ViewScope
@Component(dependencies = ApplicationComponent.class)
public interface FragmentComponent {

}
