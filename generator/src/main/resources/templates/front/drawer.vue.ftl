<#assign title="${model.title}"/>
<#assign Entity="${model.bigHump}"/>
<#assign entity="${model.smallHump}"/>
<template>
  <BasicDrawer
    v-bind="$attrs"
    @register="registerDrawer"
    showFooter
    :title="getTitle"
    width="50%"
    @ok="handleSubmit"
  >
    <BasicForm @register="registerForm" />
  </BasicDrawer>
</template>
<script lang="ts">
  import {computed, defineComponent, ref, unref} from 'vue';
  import {BasicForm, useForm} from '/@/components/Form/index';
  import {BasicDrawer, useDrawerInner} from '/@/components/Drawer';
  import {formSchema, saveApi, updateApi} from '/@';

  export default defineComponent({
    name: '${Entity}Drawer',
    components: { BasicDrawer, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const isUpdate = ref(true);
      const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
        labelWidth: 100,
        schemas: formSchema,
        showActionButtonGroup: false,
        baseColProps: { lg: 12, md: 24 },
      });

      const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
        await resetFields();
        setDrawerProps({ confirmLoading: false });
        isUpdate.value = !!data?.isUpdate;
        if (unref(isUpdate)) {
          await setFieldsValue({
            ...data.record,
            _isUpdate: isUpdate.value,
          });
        } else {
          await setFieldsValue({
            _isUpdate: isUpdate.value,
          });
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '新增${title}' : '编辑${title}'));

      async function handleSubmit() {
        try {
          const values = await validate();
          setDrawerProps({ confirmLoading: true });
          if (unref(isUpdate)) {
            await updateApi(values);
          } else {
            await saveApi(values);
          }
          closeDrawer();
          emit('success');
        } finally {
          setDrawerProps({ confirmLoading: false });
        }
      }

      return {
        registerDrawer,
        registerForm,
        getTitle,
        handleSubmit,
      };
    },
  });
</script>
<style scoped></style>
