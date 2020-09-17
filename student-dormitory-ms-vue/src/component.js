import { Menu, Submenu, MenuItem, MenuItemGroup, Input, Form, FormItem, Cascader, Timeline, TimelineItem,
         Select, Option, Button, Dialog, Table, TableColumn, Col, MessageBox, Message, DatePicker,TimePicker,
         } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

export default Vue=>{
    Vue.component(Menu.name, Menu);
    Vue.component(Submenu.name, Submenu);
    Vue.component(MenuItem.name, MenuItem);
    Vue.component(MenuItemGroup.name, MenuItemGroup);
    Vue.component(Input.name, Input);
    Vue.component(Form.name, Form);
    Vue.component(FormItem.name, FormItem);
    Vue.component(Select.name, Select);
    Vue.component(Option.name, Option);
    Vue.component(Button.name, Button);
    Vue.component(Dialog.name, Dialog);
    Vue.component(Table.name, Table);
    Vue.component(TableColumn.name, TableColumn);
    Vue.component(Col.name, Col);
    Vue.component(Cascader.name, Cascader);
    Vue.component(Timeline.name, Timeline);
    Vue.component(TimelineItem.name, TimelineItem);
    Vue.component(DatePicker.name, DatePicker);
    Vue.component(TimePicker.name, TimePicker);
    
    Vue.prototype.$msgbox = MessageBox;
    Vue.prototype.$alert = MessageBox.alert;
    Vue.prototype.$confirm = MessageBox.confirm;
    Vue.prototype.$prompt = MessageBox.prompt;
    Vue.prototype.$notify = Notification;
    Vue.prototype.$message = Message;    
}