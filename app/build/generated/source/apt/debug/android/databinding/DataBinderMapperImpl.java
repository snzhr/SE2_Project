package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.example.acer.oopproject.DataBinderMapperImpl());
    addMapper(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
  }
}
