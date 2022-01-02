package jae.jjfactory.validation.v3;

import jae.jjfactory.validation.item.Item;
import jae.jjfactory.validation.item.ItemRepositoryV2;
import jae.jjfactory.validation.ItemValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/validation/v3/items")
@Controller
@Slf4j
@RequiredArgsConstructor
public class ValidationControllerV3 {

    private final ItemRepositoryV2 itemRepositoryV2;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepositoryV2.findAll();
        model.addAttribute("items",items);
        return "validation/v3/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepositoryV2.findById(itemId);
        model.addAttribute("item",item);
        return "validation/v3/item";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item",new Item());
        return "validation/v3/addForm";
    }

    @PostMapping("/add")
    public String addItemV5(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {


        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors= {}",bindingResult);
//            model.addAttribute("errors", bindingResult);
            return "validation/v3/addForm";
        }
        //성공 로직
        Item savedItem = itemRepositoryV2.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId,Model model){
        Item item = itemRepositoryV2.findById(itemId);
        model.addAttribute("item",item);
        return "validation/v3/edit";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute Item
            item, BindingResult bindingResult) {
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000,
                        resultPrice}, null);
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v3/editForm";
        }
        itemRepositoryV2.update(itemId, item);
        return "redirect:/validation/v3/items/{itemId}";
    }
}
